package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReport;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "draft_monthly_report_submissions")
@NoArgsConstructor
@Getter
@Setter
public class DraftMonthlyReportSubmission {
    @Id
    private UUID id;
    private LocalDate submissionDate;
    private LocalDate reportStartDate;
    private LocalDate reportEndDate;
    private String draftMonthlyReportUrl;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private String details;
    private Integer numberOfApprovedHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private MonthlyReport monthlyReport;

    public DraftMonthlyReportSubmission(String draftMonthlyReportUrl, MonthlyReport monthlyReport) {
        this.id = UUID.randomUUID();
        this.submissionDate = LocalDate.now();
        this.draftMonthlyReportUrl = draftMonthlyReportUrl;
        this.status = RequestStatus.PENDING;
        this.monthlyReport = monthlyReport;
    }
}
