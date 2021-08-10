package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String postalCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String number;
    private String complement;
}
