package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InternshipType {
    REQUIRED_OR_NOT("REQUIRED_OR_NOT", "Estágio obrigatório ou não obrigatório"),
    REQUIRED("REQUIRED", "Obrigatório"),
    NOT_REQUIRED("NOT_REQUIRED", "NÃO Obrigatório"),
    PROFESSIONAL_VALIDATION("PROFESSIONAL_VALIDATION", "Aproveitamento profissional"),
    PROJECT_EQUIVALENCE("PROJECT_EQUIVALENCE", "Equiparação de projeto institucional");

    private String name;
    private String description;
}
