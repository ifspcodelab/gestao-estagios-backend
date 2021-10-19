package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.Value;

import java.util.UUID;

@Value
public class ParameterDto {
    UUID id;
    String requiredOrNot;
    String projectEquivalence;
    String professionalEnjoyment;
    Integer advisorRequestDeadline;
}
