package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Value;

import java.util.UUID;

@Value
public class CampusDto {
    UUID id;
    String name;
    String abbreviation;
    String email;
    String telephone;
    String siteSector;


}


