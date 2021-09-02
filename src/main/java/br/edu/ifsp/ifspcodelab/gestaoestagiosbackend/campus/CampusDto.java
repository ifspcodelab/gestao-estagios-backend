package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CampusDto {
    UUID id;
    String name;
    String abbreviation;
    AddressDto address;
    InternshipSectorDto internshipSector;
    EntityStatus status;
}
