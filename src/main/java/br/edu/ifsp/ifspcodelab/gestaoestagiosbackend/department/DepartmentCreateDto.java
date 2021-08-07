package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Value
public class DepartmentCreateDto {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    @Size(min = 3,max = 3)
    String abbreviation;
    @NotNull
    UUID campusId;
}
