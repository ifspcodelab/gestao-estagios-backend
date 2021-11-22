package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission;

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
@Table(name = "final_monthly_report_submissions")
@NoArgsConstructor
@Getter
@Setter
public class FinalMonthlyReportSubmission {
    @Id
    UUID id;
    private LocalDate submissionDate;
    private String finalMonthlyReportUrl;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private MonthlyReport monthlyReport;

    public FinalMonthlyReportSubmission(String finalMonthlyReportUrl, MonthlyReport monthlyReport) {
        this.id = UUID.randomUUID();
        this.submissionDate = LocalDate.now();
        this.finalMonthlyReportUrl = finalMonthlyReportUrl;
        this.status = RequestStatus.PENDING;
        this.monthlyReport = monthlyReport;
    }
}
