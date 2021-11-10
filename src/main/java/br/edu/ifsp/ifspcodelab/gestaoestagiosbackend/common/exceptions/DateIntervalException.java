package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DateIntervalException extends RuntimeException {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer interval;

    public DateIntervalException(LocalDate startDate, LocalDate endDate, Integer interval) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
        this.interval = interval;
    }
}
