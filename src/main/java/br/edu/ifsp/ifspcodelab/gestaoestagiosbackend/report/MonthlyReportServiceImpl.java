package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MonthlyReportServiceImpl implements MonthlyReportService {

    private final MonthlyReportRepository monthlyReportRepository;

    @Override
    public MonthlyReport create(LocalDate month, ActivityPlan activityPlan) {
        return monthlyReportRepository.save(new MonthlyReport(month, activityPlan));
    }

    @Override
    public MonthlyReport update(MonthlyReport monthlyReport) {
        return monthlyReportRepository.save(monthlyReport);
    }

    @Override
    public MonthlyReport findById(UUID id) {
        return monthlyReportRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.MONTHLY_REPORT, id));
    }
}
