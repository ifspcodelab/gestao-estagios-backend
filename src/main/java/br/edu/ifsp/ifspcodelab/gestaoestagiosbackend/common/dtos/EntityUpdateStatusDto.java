package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EntityUpdateStatusDto {
    @NotNull
    EntityStatus status;
}
