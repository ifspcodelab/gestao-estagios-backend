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
    private Integer activityPlanFileSizeBytes;
    private Integer monthlyReportFileSizeBytes;
    private Integer monthlyReportDraftSubmissionDeadlineMonths;
    private Integer monthlyReportDraftAppraisalDeadlineDays;
    private Integer monthlyReportAppraisalDeadlineDays;

    public Parameter(
            String internshipRequiredOrNotMessage,
            String projectEquivalenceMessage,
            String professionalValidationMessage,
            Integer advisorRequestDeadline,
            Integer activityPlanAppraisalDeadline,
            String activityPlanLink,
            Integer activityPlanFileSizeBytes,
            Integer monthlyReportFileSizeBytes,
            Integer monthlyReportDraftSubmissionDeadlineMonths,
            Integer monthlyReportDraftAppraisalDeadlineDays,
            Integer monthlyReportAppraisalDeadlineDays) {
        this.id = UUID.randomUUID();
        this.internshipRequiredOrNotMessage = internshipRequiredOrNotMessage;
        this.projectEquivalenceMessage = projectEquivalenceMessage;
        this.professionalValidationMessage = professionalValidationMessage;
        this.advisorRequestDeadline = advisorRequestDeadline;
        this.activityPlanAppraisalDeadline = activityPlanAppraisalDeadline;
        this.activityPlanLink = activityPlanLink;
        this.activityPlanFileSizeBytes = activityPlanFileSizeBytes;
        this.monthlyReportFileSizeBytes = monthlyReportFileSizeBytes;
        this.monthlyReportDraftSubmissionDeadlineMonths = monthlyReportDraftSubmissionDeadlineMonths;
        this.monthlyReportDraftAppraisalDeadlineDays = monthlyReportDraftAppraisalDeadlineDays;
        this.monthlyReportAppraisalDeadlineDays = monthlyReportAppraisalDeadlineDays;
    }
}