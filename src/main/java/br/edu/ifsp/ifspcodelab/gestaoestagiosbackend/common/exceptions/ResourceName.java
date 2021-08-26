package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceName {
    USER("User"),
    CAMPUS("Campus"),
    DEPARTMENT("Department"),
    COURSE("Course"),
    CITY("City");

    private String name;
}
