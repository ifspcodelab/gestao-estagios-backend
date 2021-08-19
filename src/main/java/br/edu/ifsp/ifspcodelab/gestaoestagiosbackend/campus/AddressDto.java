package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Cep;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Optional;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class AddressDto {
    @NotNull
    @NotBlank
    @Cep
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
    @Optional
    String complement;
}
