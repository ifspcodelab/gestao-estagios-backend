package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface DraftMonthlyReportSubmissionService {
    DraftMonthlyReportSubmission create(UUID internshipId, UUID monthlyReportId, MultipartFile file);
    DraftMonthlyReportSubmission update(
        UUID internshipId,
        UUID monthlyReportId,
        UUID draftMonthlyReportSubmissionId,
        DraftMonthlyReportSubmissionUpdateDto draftMonthlyReportSubmissionUpdateDto
    );
    DraftMonthlyReportSubmission appraise(
        UUID internshipId,
        UUID monthlyReportId,
        UUID draftMonthlyReportSubmissionId,
        DraftMonthlyReportSubmissionAppraisalDto draftMonthlyReportSubmissionAppraisalDto
    );
}
