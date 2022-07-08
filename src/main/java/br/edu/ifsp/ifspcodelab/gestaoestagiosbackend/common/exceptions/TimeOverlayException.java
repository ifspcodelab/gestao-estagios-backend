package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TimeOverlayException extends RuntimeException {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate overlaidStartDate;
    private final LocalDate overlaidEndDate;

    public TimeOverlayException(LocalDate startDate, LocalDate endDate, LocalDate overlaidStartDate, LocalDate overlaidEndDate) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
        this.overlaidStartDate = overlaidStartDate;
        this.overlaidEndDate = overlaidEndDate;
    }
}
