package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Value
public class ParameterCreateDto {
    @NotNull
    @NotBlank
    String requiredOrNot;
    @NotNull
    @NotBlank
    String projectEquivalence;
    @NotNull
    @NotBlank
    String professionalEnjoyment;
    @NotNull
    @Positive
    Integer advisorRequestDeadline;
}
