package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.SubmissionException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload.UploadService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReport;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReportService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft.DraftMonthlyReportSubmissionAlreadyExistsByStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class FinalMonthlyReportSubmissionServiceImpl implements FinalMonthlyReportSubmissionService {
    private final FinalMonthlyReportSubmissionRepository finalMonthlyReportSubmissionRepository;

    private InternshipService internshipService;
    private MonthlyReportService monthlyReportService;
    private UploadService uploadService;

    public FinalMonthlyReportSubmissionServiceImpl(FinalMonthlyReportSubmissionRepository finalMonthlyReportSubmissionRepository) {
        this.finalMonthlyReportSubmissionRepository = finalMonthlyReportSubmissionRepository;
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
}
