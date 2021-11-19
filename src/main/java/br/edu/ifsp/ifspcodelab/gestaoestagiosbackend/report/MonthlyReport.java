package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "monthly_reports")
@NoArgsConstructor
@Getter
@Setter
public class MonthlyReport {
    @Id
    private UUID id;
    private LocalDate month;
    private LocalDate acceptationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String attachmentUrl;
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
    @OneToOne
    private ActivityPlan activityPlan;

    public MonthlyReport(LocalDate month, ActivityPlan activityPlan) {
        this.id = UUID.randomUUID();
        this.month = month;
        this.status = ReportStatus.DRAFT_PENDING;
        this.activityPlan = activityPlan;
    }
}
