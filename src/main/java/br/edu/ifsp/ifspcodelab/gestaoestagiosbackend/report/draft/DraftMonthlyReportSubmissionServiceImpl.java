package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DateIntervalException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DraftDateSubmissionException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload.UploadService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReport;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class DraftMonthlyReportSubmissionServiceImpl implements DraftMonthlyReportSubmissionService {
    private final DraftMonthlyReportSubmissionRepository draftMonthlyReportSubmissionRepository;

    private InternshipService internshipService;
    private MonthlyReportService monthlyReportService;
    private UploadService uploadService;

    public DraftMonthlyReportSubmissionServiceImpl(DraftMonthlyReportSubmissionRepository draftMonthlyReportSubmissionRepository) {
        this.draftMonthlyReportSubmissionRepository = draftMonthlyReportSubmissionRepository;
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
        monthlyReportService.findById(monthlyReportId);
        DraftMonthlyReportSubmission draft = draftMonthlyReportSubmissionRepository.findById(draftMonthlyReportSubmissionId)
            .orElseThrow(
                () -> new ResourceNotFoundException(ResourceName.DRAFT_MONTHLY_REPORT_SUBMISSION, draftMonthlyReportSubmissionId)
            );

        draft.setReportStartDate(draftMonthlyReportSubmissionUpdateDto.getReportStartDate());
        draft.setReportEndDate(draftMonthlyReportSubmissionUpdateDto.getReportEndDate());

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
}
