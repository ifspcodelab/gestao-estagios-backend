package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Data;

import java.util.UUID;

@Data
public class CampusDto {
    UUID id;
    String name;
    String abbreviation;
    AddressDto address;
    InternshipSectorDto internshipSector;
}
