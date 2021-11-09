package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.Value;

import java.util.UUID;

@Value
public class ParameterDto {
    UUID id;
    String internshipRequiredOrNotMessage;
    String projectEquivalenceMessage;
    String professionalValidationMessage;
    Integer advisorRequestDeadline;
    Integer activityPlanAppraisalDeadline;
    String activityPlanLink;
    Integer activityPlanFileSizeBytes;
    Integer monthlyReportFileSizeBytes;
    Integer monthlyReportDraftSubmissionDeadlineMonths;
    Integer monthlyReportDraftAppraisalDeadlineDays;
    Integer monthlyReportAppraisalDeadlineDays;
}