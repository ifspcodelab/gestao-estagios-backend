package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class InternshipSectorDto {
    @NotNull
    @NotBlank
    String telephone;
    @NotNull
    @NotBlank
    @Email
    String email;
    @NotNull
    @NotBlank
    @URL
    String website;
}
