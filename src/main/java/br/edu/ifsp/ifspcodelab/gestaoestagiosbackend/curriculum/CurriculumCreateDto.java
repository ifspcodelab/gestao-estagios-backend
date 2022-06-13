package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Value
public class CurriculumCreateDto {
    @NotNull
    @NotBlank
    String code;
    @NotNull
    @Positive
    Integer courseLoad;
    @NotNull
    @Positive
    Integer internshipCourseLoad;
    @NotNull
    @NotBlank
    String internshipStartCriteria;
    @NotNull
    @NotBlank
    String internshipAllowedActivities;
    @NotNull
    LocalDate validityStartDate;
    @NotNull
    LocalDate validityEndDate;
}
