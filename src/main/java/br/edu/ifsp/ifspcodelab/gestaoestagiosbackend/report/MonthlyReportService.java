package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;

import java.time.LocalDate;
import java.util.UUID;

public interface MonthlyReportService {
    MonthlyReport create(LocalDate month, ActivityPlan activityPlan);
    MonthlyReport findById(UUID id);
}
