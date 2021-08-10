package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CampusCreateDto {
    @NotNull
    @NotBlank
    String name;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 3)
    String abbreviation;
    @NotNull
    @Valid
    AddressDto address;
    @NotNull
    @NotBlank
    String telephone;
    @NotNull
    @Email
    String email;
    @NotNull
    @URL
    String website;
}
