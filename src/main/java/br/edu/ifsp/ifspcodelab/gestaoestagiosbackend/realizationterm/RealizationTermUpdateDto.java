package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class RealizationTermUpdateDto {
    @NotNull
    LocalDate termStartDate;
    @NotNull
    LocalDate termEndDate;
}
