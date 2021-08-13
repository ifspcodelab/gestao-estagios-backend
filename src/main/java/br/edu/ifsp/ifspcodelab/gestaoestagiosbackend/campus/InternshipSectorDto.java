package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Builder;
import lombok.Data;

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
    String email;
    @NotNull
    @NotBlank
    String website;
}
