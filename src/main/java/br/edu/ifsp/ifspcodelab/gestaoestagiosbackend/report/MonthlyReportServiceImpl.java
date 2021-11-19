package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MonthlyReportServiceImpl implements MonthlyReportService {

    private final MonthlyReportRepository monthlyReportRepository;

    @Override
    public MonthlyReport create(LocalDate month, ActivityPlan activityPlan) {
        return monthlyReportRepository.save(new MonthlyReport(month, activityPlan));
    }
}
