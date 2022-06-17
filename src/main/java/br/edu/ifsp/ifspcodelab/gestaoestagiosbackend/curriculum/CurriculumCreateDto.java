package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.text.DateFormat;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate validityStartDate;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate validityEndDate;
}
