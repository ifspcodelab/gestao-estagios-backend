package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft.DraftMonthlyReportSubmission;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission.FinalMonthlyReportSubmission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
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
    private Boolean draftSubmittedOnDeadline;
    private LocalDate finalAcceptationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String attachmentUrl;
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @OneToOne
    private ActivityPlan activityPlan;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<DraftMonthlyReportSubmission> draftMonthlyReportSubmissions;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<FinalMonthlyReportSubmission> finalMonthlyReportSubmissions;

    public MonthlyReport(LocalDate month, ActivityPlan activityPlan) {
        this.id = UUID.randomUUID();
        this.month = month;
        this.status = ReportStatus.DRAFT_PENDING;
        this.activityPlan = activityPlan;
    }

    public void addDraftMonthlyReportSubmission(DraftMonthlyReportSubmission draftMonthlyReportSubmission) {
        if (this.draftMonthlyReportSubmissions.isEmpty()) {
            this.draftSubmittedOnDeadline = !LocalDate.now().withDayOfMonth(1).isAfter(this.month.plusMonths(2));
        }
        this.draftMonthlyReportSubmissions.add(draftMonthlyReportSubmission);
        this.status = ReportStatus.DRAFT_SENT;
    }

    public void addFinalMonthlyReportSubmission(FinalMonthlyReportSubmission finalMonthlyReportSubmission) {
        this.finalMonthlyReportSubmissions.add(finalMonthlyReportSubmission);
        this.status = ReportStatus.FINAL_SENT;
    }
}
