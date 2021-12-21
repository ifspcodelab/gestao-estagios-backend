package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft.DraftMonthlyReportSubmission;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission.FinalMonthlyReportSubmission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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
    private Integer numberOfApprovedHours;
    private String finalMonthlyReportUrl;
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Internship internship;
    @ManyToOne
    private ActivityPlan activityPlan;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "monthlyReport")
    @JsonManagedReference
    private Set<DraftMonthlyReportSubmission> draftMonthlyReportSubmissions;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "monthlyReport")
    @JsonManagedReference
    private Set<FinalMonthlyReportSubmission> finalMonthlyReportSubmissions;

    public MonthlyReport(LocalDate month, ActivityPlan activityPlan, Internship internship) {
        this.id = UUID.randomUUID();
        this.month = month;
        this.status = ReportStatus.DRAFT_PENDING;
        this.activityPlan = activityPlan;
        this.internship = internship;
        this.draftMonthlyReportSubmissions = new HashSet<>();
        this.finalMonthlyReportSubmissions = new HashSet<>();
    }

    public void addDraftMonthlyReportSubmission(DraftMonthlyReportSubmission draftMonthlyReportSubmission) {
        if (this.draftMonthlyReportSubmissions.isEmpty()) {
            this.draftSubmittedOnDeadline = !(LocalDate.now().isAfter(this.month.plusMonths(2)) || LocalDate.now().isEqual(this.month.plusMonths(2)));
        }
        this.draftMonthlyReportSubmissions.add(draftMonthlyReportSubmission);
        this.status = ReportStatus.DRAFT_SENT;
    }

    public void addFinalMonthlyReportSubmission(FinalMonthlyReportSubmission finalMonthlyReportSubmission) {
        this.finalMonthlyReportSubmissions.add(finalMonthlyReportSubmission);
        this.status = ReportStatus.FINAL_SENT;
    }
}
