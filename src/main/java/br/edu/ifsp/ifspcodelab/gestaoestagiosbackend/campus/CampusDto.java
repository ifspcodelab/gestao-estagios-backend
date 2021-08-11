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

    public CampusDto(Campus campus) {
        this.id = campus.getId();
        this.name = campus.getName();
        this.abbreviation = campus.getAbbreviation();
        this.address = new AddressDto(campus.getAddress());
        this.internshipSector = new InternshipSectorDto(campus.getInternshipSector());
    }
}
