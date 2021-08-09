package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;


import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class CampusCreateDto {

    @NotNull
    @NotBlank
    String name;

    @Size(max=10)
    String abbreviation;

    @Email
    String email;

    String telephone;

    String siteSector;

}
