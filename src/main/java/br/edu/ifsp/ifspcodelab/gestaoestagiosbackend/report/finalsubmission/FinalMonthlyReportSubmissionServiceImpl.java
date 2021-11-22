package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.SubmissionException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
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
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft.DraftMonthlyReportSubmissionAlreadyExistsByStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Service
public class FinalMonthlyReportSubmissionServiceImpl implements FinalMonthlyReportSubmissionService {
    private final FinalMonthlyReportSubmissionRepository finalMonthlyReportSubmissionRepository;
    private final SenderMail senderMail;

    private InternshipService internshipService;
    private MonthlyReportService monthlyReportService;
    private UploadService uploadService;

    @Value("${application.frontend.url}")
    private String frontendUrl;

    public FinalMonthlyReportSubmissionServiceImpl(
        FinalMonthlyReportSubmissionRepository finalMonthlyReportSubmissionRepository,
        SenderMail senderMail
    ) {
        this.finalMonthlyReportSubmissionRepository = finalMonthlyReportSubmissionRepository;
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
    public FinalMonthlyReportSubmission create(UUID internshipId, UUID monthlyReportId, MultipartFile file) {
        Internship internship = internshipService.findById(internshipId);
        MonthlyReport monthlyReport = monthlyReportService.findById(monthlyReportId);

        monthlyReport.getFinalMonthlyReportSubmissions().forEach(d -> {
            if (d.getStatus() == RequestStatus.PENDING) {
                throw new DraftMonthlyReportSubmissionAlreadyExistsByStatus(d.getStatus());
            }
        });
        if (monthlyReport.getStatus() != ReportStatus.FINAL_PENDING) {
            throw new SubmissionException(monthlyReport.getStatus());
        }

        uploadService.monthlyReportFileValidation(file);
        String finalMonthlyReportSubmissionUrl = uploadService.uploadFile(file, getFileName(internship));

        FinalMonthlyReportSubmission finalSubmission = new FinalMonthlyReportSubmission(finalMonthlyReportSubmissionUrl);
        finalMonthlyReportSubmissionRepository.save(finalSubmission);

        monthlyReport.addFinalMonthlyReportSubmission(finalSubmission);
        monthlyReportService.update(monthlyReport);

        sendEmailFinalSentToStudent(monthlyReport);
        sendEmailFinalSentToAdvisor(monthlyReport);

        return finalSubmission;
    }

    @Override
    public FinalMonthlyReportSubmission appraise(
        UUID internshipId,
        UUID monthlyReportId,
        UUID finalMonthlyReportSubmissionId,
        FinalMonthlyReportSubmissionAppraisalDto finalMonthlyReportSubmissionAppraisalDto)
    {
        internshipService.findById(internshipId);
        MonthlyReport monthlyReport = monthlyReportService.findById(monthlyReportId);
        FinalMonthlyReportSubmission finalSubmission = getFinalSubmission(finalMonthlyReportSubmissionId);

        if (finalSubmission.getStatus() == RequestStatus.ACCEPTED || finalSubmission.getStatus() == RequestStatus.REJECTED) {
            throw new FinalMonthlyReportSubmissionAlreadyExistsByStatus(finalSubmission.getStatus());
        }

        if (finalMonthlyReportSubmissionAppraisalDto.getStatus() == RequestStatus.ACCEPTED) {
            monthlyReport.setStatus(ReportStatus.FINAL_ACCEPTED);
            monthlyReport.setFinalAcceptationDate(LocalDate.now());
        } else {
            monthlyReport.setStatus(ReportStatus.FINAL_PENDING);
        }
        sendEmailFinalAppraisal(monthlyReport, finalMonthlyReportSubmissionAppraisalDto);

        finalSubmission.setDetails(finalMonthlyReportSubmissionAppraisalDto.getDetails());
        finalSubmission.setStatus(finalMonthlyReportSubmissionAppraisalDto.getStatus());

        monthlyReportService.update(monthlyReport);
        return finalMonthlyReportSubmissionRepository.save(finalSubmission);
    }

    private String getFileName(Internship internship) {
        return internship.getAdvisorRequest().getStudent().getUser().getRegistration() +
            "/" +
            internship.getId().toString() +
            "/" +
            System.currentTimeMillis() +
            "-relatorio-mensal";
    }

    private FinalMonthlyReportSubmission getFinalSubmission(UUID finalMonthlyReportSubmissionId) {
        return finalMonthlyReportSubmissionRepository.findById(finalMonthlyReportSubmissionId)
            .orElseThrow(
                () -> new ResourceNotFoundException(ResourceName.FINAL_MONTHLY_REPORT_SUBMISSION, finalMonthlyReportSubmissionId)
            );
    }

    private void sendEmailFinalSentToStudent(MonthlyReport monthlyReport) {
        MailDto email = MailDto.builder()
            .title("Relatório mensal")
            .msgHTML(TemplatesHtml.getReportSentToStudent())
            .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM 'de' yyyy");
        Map<String, String> params = CreatorParametersMail.setParametersReportSentToStudent(
            "Relatório mensal",
            monthlyReport.getMonth().format(formatter)
        );
        email = FormatterMail.build(email, params);

        email.setRecipientTo(
            monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getEmail()
        );
        senderMail.sendEmail(email);
    }

    private void sendEmailFinalSentToAdvisor(MonthlyReport monthlyReport) {
        MailDto email = MailDto.builder()
            .title("Relatório mensal recebido - [" +
                monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getName() +
                "]"
            )
            .msgHTML(TemplatesHtml.getReportSentToAdvisor())
            .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM 'de' yyyy");
        Map<String, String> params = CreatorParametersMail.setParametersReportSentToAdvisor(
            "Relatório mensal",
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

    private void sendEmailFinalAppraisal(MonthlyReport monthlyReport, FinalMonthlyReportSubmissionAppraisalDto dto) {
        MailDto email;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM 'de' yyyy");
        Map<String, String> params;

        if (dto.getStatus() == RequestStatus.ACCEPTED) {
            email = MailDto.builder()
                .title("Deferimento de relatório mensal")
                .msgHTML(TemplatesHtml.getReportApproved())
                .build();

            params = CreatorParametersMail.setParametersReportApproved(
                "relatório mensal",
                monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getStudent().getUser().getName(),
                monthlyReport.getActivityPlan().getInternship().getAdvisorRequest().getAdvisor().getUser().getName(),
                monthlyReport.getMonth().format(formatter),
                dto.getDetails()
            );
        } else {
            email = MailDto.builder()
                .title("Indeferimento de relatório mensal")
                .msgHTML(TemplatesHtml.getReportIndeferred())
                .build();

            params = CreatorParametersMail.setParametersReportIndeferred(
                "relatório mensal",
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
