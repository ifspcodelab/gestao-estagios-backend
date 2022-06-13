package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Alpha;
import lombok.Value;

import javax.validation.constraints.*;
import java.util.UUID;

@Value
public class CourseCreateDto {
    @NotNull
    @NotBlank
    @Alpha
    String name;
    @NotNull
    @NotBlank
    @Size(max = 10)
    @Alpha
    String abbreviation;
    @NotNull
    @Positive
    @Max(value = 20)
    Integer numberOfPeriods;
    @NotNull
    UUID departmentId;
}
