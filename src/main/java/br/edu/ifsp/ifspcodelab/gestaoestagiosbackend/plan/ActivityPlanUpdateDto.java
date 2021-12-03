package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class ActivityPlanUpdateDto {
    String companyName;
    @NotNull
    LocalDate internshipStartDate;
    @NotNull
    LocalDate internshipEndDate;
}
