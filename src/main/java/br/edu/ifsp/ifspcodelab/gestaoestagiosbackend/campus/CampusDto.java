package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Data;

import java.util.UUID;

@Data
public class CampusDto {
    UUID id;
    String name;
    String abbreviation;
    AddressDto address;
    String telephone;
    String email;
    String website;

    public CampusDto(Campus campus) {
        this.id = campus.getId();
        this.name = campus.getName();
        this.abbreviation = campus.getAbbreviation();
        this.address = new AddressDto(campus.getAddress());
        this.telephone = campus.getTelephone();
        this.email = campus.getEmail();
        this.website = campus.getWebsite();
    }
}
