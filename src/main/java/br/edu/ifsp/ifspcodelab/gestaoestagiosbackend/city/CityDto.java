package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import lombok.Value;

import java.util.UUID;

@Value
public class CityDto {
    UUID id;
    String name;
    State state;
}
