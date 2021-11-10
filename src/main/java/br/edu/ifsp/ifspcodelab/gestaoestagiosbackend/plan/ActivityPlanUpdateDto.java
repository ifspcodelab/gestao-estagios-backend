package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class ActivityPlanUpdateDto {
    @NotNull
    @NotBlank
    String companyName;
    @NotNull
    LocalDate internshipStartDate;
    @NotNull
    LocalDate internshipEndDate;
}
