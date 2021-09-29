package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import lombok.Value;

import javax.validation.constraints.*;
import java.util.UUID;

@Value
public class UserStudentCreateDto {
    @NotNull
    @NotBlank
    String registration;

    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 22)
    String password;

    @NotNull
    @NotBlank
    @Pattern(regexp="^([_A-Za-z0-9-+]+\\.?[_A-Za-z0-9-+]+@(aluno.ifsp.edu.br|ifsp.edu.br))$", message = "utilize o email institucional (@ifsp.edu.br ou @aluno.ifsp.edu.br)")
    String email;

    @NotNull
    UUID curriculumId;
}
