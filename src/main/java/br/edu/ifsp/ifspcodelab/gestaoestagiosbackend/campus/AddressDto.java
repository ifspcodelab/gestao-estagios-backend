package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddressDto {
    @NotNull
    @NotBlank
    String postalCode;
    @NotNull
    @NotBlank
    String street;
    @NotNull
    @NotBlank
    String neighborhood;
    @NotNull
    @NotBlank
    String city;
    @NotNull
    @NotBlank
    String state;
    @NotNull
    @NotBlank
    String number;
    @NotNull
    @NotBlank
    String complement;

    public AddressDto(Address address) {
        this.postalCode = address.getPostalCode();
        this.street = address.getStreet();
        this.neighborhood = address.getNeighborhood();
        this.city = address.getCity();
        this.state = address.getState();
        this.number = address.getNumber();
        this.complement = address.getComplement();
    }
}
