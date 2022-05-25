package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Alpha;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class CampusCreateDto {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 3)
    String abbreviation;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 2)
    @Alpha
    String initialRegistrationPattern;
    @NotNull
    @Valid
    AddressCreateDto address;
    @NotNull
    @Valid
    InternshipSectorDto internshipSector;
}
