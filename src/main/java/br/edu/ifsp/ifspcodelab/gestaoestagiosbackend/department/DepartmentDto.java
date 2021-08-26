package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class DepartmentDto {
    UUID id;
    String name;
    String abbreviation;
    EntityStatus status;
}
