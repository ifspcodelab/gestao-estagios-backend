package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DateIntervalException;

import java.time.LocalDate;

public class DateIntervalYearException extends DateIntervalException {
    public DateIntervalYearException(LocalDate startDate, LocalDate endDate, Integer interval) {
        super(startDate, endDate, interval);
    }
}
