package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class DraftMonthlyReportSubmissionAppraisalDto {
    @NotNull
    RequestStatus status;
    @NotNull
    String details;
    Integer numberOfApprovedHours;
}
