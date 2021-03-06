package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface MonthlyReportRepository extends JpaRepository<MonthlyReport, UUID> {
    void deleteAllByActivityPlanIdAndMonthAfter(UUID activityPlanId, LocalDate month);
}
