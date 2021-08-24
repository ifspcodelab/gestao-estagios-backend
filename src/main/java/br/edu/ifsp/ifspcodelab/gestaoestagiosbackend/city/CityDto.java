package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import lombok.Value;

import java.util.UUID;

@Value
public class CityDto {
    UUID id;
    String name;
}
