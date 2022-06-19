package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DateChronologyException extends RuntimeException {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DateChronologyException(LocalDate startDate, LocalDate endDate) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
