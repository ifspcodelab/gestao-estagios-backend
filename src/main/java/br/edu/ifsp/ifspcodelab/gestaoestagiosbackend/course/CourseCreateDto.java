package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Value
public class CourseCreateDto {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    @Size(max = 10)
    String abbreviation;
    @NotNull
    @Min(1)
    Integer numberOfPeriods;
    @NotNull
    UUID departmentId;
}
