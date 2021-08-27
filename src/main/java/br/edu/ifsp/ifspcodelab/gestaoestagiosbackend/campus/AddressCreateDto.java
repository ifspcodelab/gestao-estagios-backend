package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Cep;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Optional;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Builder
public class AddressCreateDto {
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
    UUID cityId;
    @NotNull
    @NotBlank
    String number;
    @Optional
    String complement;
}
