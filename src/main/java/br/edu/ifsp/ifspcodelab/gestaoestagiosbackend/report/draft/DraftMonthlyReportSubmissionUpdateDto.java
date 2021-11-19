package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class DraftMonthlyReportSubmissionUpdateDto {
    @NotNull
    LocalDate reportStartDate;
    @NotNull
    LocalDate reportEndDate;
}
