package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DraftMonthlyReportSubmissionRepository extends JpaRepository<DraftMonthlyReportSubmission, UUID> {
}
