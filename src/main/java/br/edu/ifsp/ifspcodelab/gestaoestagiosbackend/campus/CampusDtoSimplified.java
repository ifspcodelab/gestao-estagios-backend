package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Data;

import java.util.UUID;

@Data
public class CampusDtoSimplified {
    UUID id;
    String name;
    String abbreviation;
    String initialRegistrationPatter;
}
