package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private RequestStatus status;
    private String details;

    public FinalMonthlyReportSubmission(String finalMonthlyReportUrl) {
        this.id = UUID.randomUUID();
        this.submissionDate = LocalDate.now();
        this.finalMonthlyReportUrl = finalMonthlyReportUrl;
        this.status = RequestStatus.PENDING;
    }
}
