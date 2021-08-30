package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/states/{stateAbbreviation}/cities")
@CrossOrigin
@AllArgsConstructor
public class CityRestController {
    private CityRepository cityRepository;

    @GetMapping
    public ResponseEntity<List<CityDto>> index(@PathVariable String stateAbbreviation) {
        List<CityDto> cities = cityRepository.findAllByStateAbbreviation(stateAbbreviation.toUpperCase()).stream()
            .map(city -> new CityDto(city.getId(), city.getName(), city.getState())).collect(Collectors.toList());
        return ResponseEntity.ok(cities);
    }
}
