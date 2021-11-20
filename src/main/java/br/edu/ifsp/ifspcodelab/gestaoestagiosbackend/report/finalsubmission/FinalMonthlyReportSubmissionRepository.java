package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FinalMonthlyReportSubmissionRepository extends JpaRepository<FinalMonthlyReportSubmission, UUID> {
}
