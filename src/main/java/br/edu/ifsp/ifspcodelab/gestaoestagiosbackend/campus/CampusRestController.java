package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/campuses")
@AllArgsConstructor
public class CampusRestController {
    private final CampusService campusService;

    @PostMapping
    public ResponseEntity<CampusDto> create(@Valid @RequestBody CampusCreateDto campusCreateDto) {
        Campus campus = campusService.create(campusCreateDto);
        return new ResponseEntity<CampusDto>(new CampusDto(campus), HttpStatus.CREATED);
    }
}
