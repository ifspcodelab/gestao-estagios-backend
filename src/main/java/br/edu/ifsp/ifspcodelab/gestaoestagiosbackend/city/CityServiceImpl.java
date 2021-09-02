package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    CityRepository cityRepository;

    @Override
    public Optional<City> findById(UUID id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<City> findAllByStateAbbreviation(String stateAbbreviation) {
        return cityRepository.findAllByStateAbbreviation(stateAbbreviation);
    }
}
