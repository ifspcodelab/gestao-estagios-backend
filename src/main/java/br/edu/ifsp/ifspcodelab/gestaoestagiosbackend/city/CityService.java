package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityService {
    Optional<City> findById(UUID id);
    List<City> findAllByStateAbbreviation(String stateAbbreviation);
}
