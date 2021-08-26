package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

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
    UUID cityId;
    @NotNull
    @NotBlank
    String number;
    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
    String complement;
}
