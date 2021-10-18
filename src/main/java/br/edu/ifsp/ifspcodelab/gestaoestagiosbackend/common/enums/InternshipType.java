package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InternshipType {
    REQUIRED_OR_NOT("REQUIRED_OR_NOT"),
    PROFESSIONAL_ENJOYMENT("PROFESSIONAL_ENJOYMENT"),
    PROJECT_EQUIVALENCE("PROJECT_EQUIVALENCE");

    private String name;
}
