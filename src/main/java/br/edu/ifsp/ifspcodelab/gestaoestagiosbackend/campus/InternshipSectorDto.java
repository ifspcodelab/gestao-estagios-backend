package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
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

    public InternshipSectorDto(InternshipSector internshipSector) {
        this.telephone = internshipSector.getTelephone();
        this.email = internshipSector.getEmail();
        this.website = internshipSector.getWebsite();
    }
}
