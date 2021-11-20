package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FinalMonthlyReportSubmissionService {
    FinalMonthlyReportSubmission create(UUID internshipId, UUID monthlyReportId, MultipartFile file);
    FinalMonthlyReportSubmission appraise(
        UUID internshipId,
        UUID monthlyReportId,
        UUID finalMonthlyReportSubmissionId,
        FinalMonthlyReportSubmissionAppraisalDto finalMonthlyReportSubmissionAppraisalDtoDto
    );
}
