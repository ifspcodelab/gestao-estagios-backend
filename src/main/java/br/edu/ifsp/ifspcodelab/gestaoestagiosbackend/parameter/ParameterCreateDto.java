package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constrains.Max;

@Value
public class ParameterCreateDto {
    @NotNull
    @NotBlank
    String internshipRequiredOrNotMessage;
    @NotNull
    @NotBlank
    String projectEquivalenceMessage;
    @NotNull
    @NotBlank
    String professionalValidationMessage;
    @NotNull
    @Positive
    @Max(value = 12)
    Integer advisorRequestDeadline;
    @NotNull
    @Positive
    Integer activityPlanAppraisalDeadline;
    @NotNull
    @NotBlank
    String activityPlanLink;
    @NotNull
    @Positive
    Integer activityPlanFileSizeMegabytes;
    @NotNull
    @Positive
    Integer monthlyReportFileSizeMegabytes;
    @NotNull
    @Positive
    Integer monthlyReportDraftSubmissionDeadlineMonths;
    @NotNull
    @Positive
    Integer monthlyReportDraftAppraisalDeadlineDays;
    @NotNull
    @Positive
    Integer monthlyReportAppraisalDeadlineDays;
    @NotNull
    @NotBlank
    String initialDispatchHtml;
    @NotNull
    @NotBlank
    String finalDispatchHtml;
}