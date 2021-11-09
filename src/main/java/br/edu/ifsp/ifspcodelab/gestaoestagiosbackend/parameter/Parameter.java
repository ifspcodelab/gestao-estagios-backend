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

    private Integer deadlineEvaluationActivityPlan;
    private String activityPlanLink;
    private Integer deadlineSubmissionMonthlyDraft;
    private Integer deadlineEvaluationMonthlyDraft;
    private Integer evaluationPeriodMonthlyReport;

    public Parameter(String internshipRequiredOrNotMessage, String projectEquivalenceMessage, String professionalValidationMessage, Integer advisorRequestDeadline, Integer deadlineEvaluationActivityPlan, String activityPlanLink, Integer deadlineSubmissionMonthlyDraft, Integer deadlineEvaluationMonthlyDraft, Integer evaluationPeriodMonthlyReport ) {
        this.id = UUID.randomUUID();
        this.internshipRequiredOrNotMessage = internshipRequiredOrNotMessage;
        this.projectEquivalenceMessage = projectEquivalenceMessage;
        this.professionalValidationMessage = professionalValidationMessage;
        this.advisorRequestDeadline = advisorRequestDeadline;
        this.deadlineEvaluationActivityPlan = deadlineEvaluationActivityPlan;
        this.activityPlanLink = activityPlanLink;
        this.deadlineSubmissionMonthlyDraft = deadlineSubmissionMonthlyDraft;
        this.deadlineEvaluationMonthlyDraft = deadlineEvaluationMonthlyDraft;
        this.evaluationPeriodMonthlyReport = evaluationPeriodMonthlyReport;
    }
}