package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
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
}
