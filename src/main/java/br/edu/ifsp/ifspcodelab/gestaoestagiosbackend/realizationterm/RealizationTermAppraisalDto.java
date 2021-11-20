package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class RealizationTermAppraisalDto {
    @NotNull
    RequestStatus status;
    @NotNull
    @NotBlank
    String details;
}
