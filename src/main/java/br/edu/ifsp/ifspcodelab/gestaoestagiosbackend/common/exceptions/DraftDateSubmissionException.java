package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DraftDateSubmissionException extends RuntimeException {
    private final LocalDate monthlyReportDate;
    private final LocalDate actualDate;

    public DraftDateSubmissionException(LocalDate monthlyReportDate, LocalDate actualDate) {
        super();
        this.monthlyReportDate = monthlyReportDate;
        this.actualDate = actualDate;
    }
}
