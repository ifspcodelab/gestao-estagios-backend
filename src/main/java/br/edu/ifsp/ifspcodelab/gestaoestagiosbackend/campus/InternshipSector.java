package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class InternshipSector {
    private String telephone;
    private String email;
    private String website;
}
