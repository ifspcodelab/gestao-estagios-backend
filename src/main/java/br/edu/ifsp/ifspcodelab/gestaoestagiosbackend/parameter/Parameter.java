package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "parameters")
@NoArgsConstructor
@Getter
@Setter
public class Parameter {
    @Id
    private UUID id;

    private String internshipRequiredOrNotMessage;
    private String projectEquivalenceMessage;
    private String professionalValidationMessage;
    private Integer advisorRequestDeadline;
    private Integer activityPlanAppraisalDeadline;
    private String activityPlanLink;
    private Integer activityPlanFileSizeMegabytes;
    private Integer monthlyReportFileSizeMegabytes;
    private Integer monthlyReportDraftSubmissionDeadlineMonths;
    private Integer monthlyReportDraftAppraisalDeadlineDays;
    private Integer monthlyReportAppraisalDeadlineDays;
    private String initialDispatchHtml;
    private String finalDispatchHtml;
}