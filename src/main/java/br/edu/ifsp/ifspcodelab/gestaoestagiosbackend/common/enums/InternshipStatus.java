package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InternshipStatus {
    ACTIVITY_PLAN_PENDING("ACTIVITY_PLAN_PENDING"),
    ACTIVITY_PLAN_SENT("ACTIVITY_PLAN_SENT"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISHED("FINISHED");

    private String name;
}
