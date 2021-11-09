package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    Integer advisorRequestDeadline;
    @NotNull
    @Positive
    Integer activityPlanAppraisalDeadline;
    @NotNull
    @NotBlank
    String activityPlanLink;
    @NotNull
    @Positive
    Integer activityPlanFileSizeBytes;
    @NotNull
    @Positive
    Integer monthlyReportFileSizeBytes;
    @NotNull
    @Positive
    Integer monthlyReportDraftSubmissionDeadlineMonths;
    @NotNull
    @Positive
    Integer monthlyReportDraftAppraisalDeadlineDays;
    @NotNull
    @Positive
    Integer monthlyReportAppraisalDeadlineDays;
}