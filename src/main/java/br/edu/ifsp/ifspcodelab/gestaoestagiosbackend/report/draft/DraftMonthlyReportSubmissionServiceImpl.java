package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DraftDateSubmissionException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.SubmissionException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.MailDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.SenderMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.CreatorParametersMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.FormatterMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount.TemplatesHtml;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload.UploadService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReport;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Service
public class DraftMonthlyReportSubmissionServiceImpl implements DraftMonthlyReportSubmissionService {
    private final DraftMonthlyReportSubmissionRepository draftMonthlyReportSubmissionRepository;
    private final SenderMail senderMail;

    private InternshipService internshipService;
    private MonthlyReportService monthlyReportService;
    private UploadService uploadService;

    @Value("${application.frontend.url}")
    private String frontendUrl;

    public DraftMonthlyReportSubmissionServiceImpl(DraftMonthlyReportSubmissionRepository draftMonthlyReportSubmissionRepository, SenderMail senderMail) {
        this.draftMonthlyReportSubmissionRepository = draftMonthlyReportSubmissionRepository;
        this.senderMail = senderMail;
    }

    @Autowired
    public void setInternshipService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @Autowired
    public void setMonthlyReportService(MonthlyReportService monthlyReportService) {
        this.monthlyReportService = monthlyReportService;
    }

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Override
    public DraftMonthlyReportSubmission create(UUID internshipId, UUID monthlyReportId, MultipartFile file) {
        Internship internship = internshipService.findById(internshipId);
        MonthlyReport monthlyReport = monthlyReportService.findById(monthlyReportId);

        monthlyReport.getDraftMonthlyReportSubmissions().forEach(d -> {
            if (d.getStatus() == RequestStatus.PENDING) {
                throw new DraftMonthlyReportSubmissionAlreadyExistsByStatus(d.getStatus());
            }
        });
        if (monthlyReport.getStatus() != ReportStatus.DRAFT_PENDING) {
            throw new SubmissionException(monthlyReport.getStatus());
        }
        if (LocalDate.now().withDayOfMonth(1).isBefore(monthlyReport.getMonth())) {
            throw new DraftDateSubmissionException(
                monthlyReport.getMonth(),
                LocalDate.now().withDayOfMonth(1)
            );
        }

        uploadService.monthlyReportFileValidation(file);
        String draftMonthlyReportSubmissionUrl = uploadService.uploadFile(file, getFileName(internship));

        DraftMonthlyReportSubmission draft = new DraftMonthlyReportSubmission(draftMonthlyReportSubmissionUrl);
        draftMonthlyReportSubmissionRepository.save(draft);

        monthlyReport.addDraftMonthlyReportSubmission(draft);
        monthlyReportService.update(monthlyReport);

        return draft;
    }

    @Override
    public DraftMonthlyReportSubmission update(
        UUID internshipId,
        UUID monthlyReportId,
        UUID draftMonthlyReportSubmissionId,
        DraftMonthlyReportSubmissionUpdateDto draftMonthlyReportSubmissionUpdateDto
    ) {
        internshipService.findById(internshipId);
        MonthlyReport monthlyReport = monthlyReportService.findById(monthlyReportId);
        DraftMonthlyReportSubmission draft = getDraft(draftMonthlyReportSubmissionId);

        draft.setReportStartDate(draftMonthlyReportSubmissionUpdateDto.getReportStartDate());
        draft.setReportEndDate(draftMonthlyReportSubmissionUpdateDto.getReportEndDate());

        sendEmailDraftSentToStudent(monthlyReport);
        sendEmailDraftSentToAdvisor(monthlyReport);
        return draftMonthlyReportSubmissionRepository.save(draft);
    }

    @Override
    public DraftMonthlyReportSubmission appraise(
        UUID internshipId,
        UUID monthlyReportId,
        UUID draftMonthlyReportId,
        DraftMonthlyReportSubmissionAppraisalDto draftMonthlyReportSubmissionAppraisalDto
    ) {
        internshipService.findById(internshipId);
        MonthlyReport monthlyReport = monthlyReportService.findById(monthlyReportId);
        DraftMonthlyReportSubmission draft = getDraft(draftMonthlyReportId);

        if (draft.getStatus() == RequestStatus.ACCEPTED || draft.getStatus() == RequestStatus.REJECTED) {
            throw new DraftMonthlyReportSubmissionAlreadyExistsByStatus(draft.getStatus());
        }

        if (draftMonthlyReportSubmissionAppraisalDto.getStatus() == RequestStatus.ACCEPTED) {
            monthlyReport.setStatus(ReportStatus.FINAL_PENDING);
            monthlyReport.setStartDate(draft.getReportStartDate());
            monthlyReport.setEndDate(draft.getReportEndDate());

            draft.setNumberOfApprovedHours(draftMonthlyReportSubmissionAppraisalDto.getNumberOfApprovedHours());
        } else {
            monthlyReport.setStatus(ReportStatus.DRAFT_PENDING);
        }
        sendEmailDraftAppraisal(monthlyReport, draftMonthlyReportSubmissionAppraisalDto);

        draft.setDetails(draftMonthlyReportSubmissionAppraisalDto.getDetails());
        draft.setStatus(draftMonthlyReportSubmissionAppraisalDto.getStatus());

        monthlyReportService.update(monthlyReport);
        return draftMonthlyReportSubmissionRepository.save(draft);
    }

    private String getFileName(Internship internship) {
        return internship.getAdvisorRequest().getStudent().getUser().getRegistration() +
            "/" +
            internship.getId().toString() +
            "/" +
            System.currentTimeMillis() +
            "-rascunho-relatorio-mensal";
    }

    private DraftMonthlyReportSubmission getDraft(UUID draftMonthlyReportSubmissionId) {
        return draftMonthlyReportSubmissionRepository.findById(draftMonthlyReportSubmissionId)
            .orElseThrow(
                () -> new ResourceNotFoundException(ResourceName.DRAFT_MONTHLY_REPORT_SUBMISSION, draftMonthlyReportSubmissionId)
            );
    }

    private void sendEmailDraftSentToStudent(MonthlyReport monthlyReport) {
        MailDto email = MailDto.builder()
            .title("Envio de rascunho do relatório mensal")
            .msgHTML(TemplatesHtml.getReportSentToStudent())
            .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM 'de' yyyy");
        Map<String, String> params = CreatorParametersMail.setParametersReportSentToStudent(
            "rascunho de relatório mensal",
            monthlyReport.getMonth().format(formatter)
        );
        email = FormatterMail.build(email, params);

        email.setRecipientTo(
            monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getEmail()
        );
        senderMail.sendEmail(email);
    }

    private void sendEmailDraftSentToAdvisor(MonthlyReport monthlyReport) {
        MailDto email = MailDto.builder()
            .title("Rascunho de relatório mensal recebido - [" +
                monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getName() +
                "]"
            )
            .msgHTML(TemplatesHtml.getReportSentToAdvisor())
            .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM 'de' yyyy");
        Map<String, String> params = CreatorParametersMail.setParametersReportSentToAdvisor(
            "rascunho de relatório mensal",
            monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getName(),
            monthlyReport.getMonth().format(formatter),
            frontendUrl
        );
        email = FormatterMail.build(email, params);

        email.setRecipientTo(
            monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getAdvisor().getUser().getEmail()
        );
        senderMail.sendEmail(email);
    }

    private void sendEmailDraftAppraisal(MonthlyReport monthlyReport, DraftMonthlyReportSubmissionAppraisalDto dto) {
        MailDto email;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM 'de' yyyy");
        Map<String, String> params;

        if (dto.getStatus() == RequestStatus.ACCEPTED) {
            email = MailDto.builder()
                .title("Deferimento de rascunho do relatório mensal")
                .msgHTML(TemplatesHtml.getReportApproved())
                .build();

            params = CreatorParametersMail.setParametersReportApproved(
                "rascunho do relatório mensal",
                monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getName(),
                monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getAdvisor().getUser().getName(),
                monthlyReport.getMonth().format(formatter),
                dto.getDetails()
            );
        } else {
            email = MailDto.builder()
                .title("Indeferimento de rascunho do relatório mensal")
                .msgHTML(TemplatesHtml.getReportIndeferred())
                .build();

            params = CreatorParametersMail.setParametersReportIndeferred(
                "rascunho do relatório mensal",
                monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getName(),
                monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getAdvisor().getUser().getName(),
                monthlyReport.getMonth().format(formatter),
                dto.getDetails(),
                frontendUrl
            );
        }

        email = FormatterMail.build(email, params);
        email.setRecipientTo(
            monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getEmail()
        );
        senderMail.sendEmail(email);
    }
}
