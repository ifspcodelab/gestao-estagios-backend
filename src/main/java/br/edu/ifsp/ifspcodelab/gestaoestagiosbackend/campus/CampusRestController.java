package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/campuses")
@AllArgsConstructor
public class CampusRestController {
    private final CampusService campusService;

    @PostMapping
    public ResponseEntity<CampusDto> create(@Valid @RequestBody CampusCreateDto campusCreateDto) {
        Campus campus = campusService.create(campusCreateDto);
        return new ResponseEntity<>(new CampusDto(campus), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CampusDto>> index() {
        List<CampusDto> campuses = campusService.findAll().stream()
            .map(CampusDto::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(campuses);
    }

    @GetMapping("{id}")
    public ResponseEntity<CampusDto> show(@PathVariable UUID id) {
        return ResponseEntity.ok(new CampusDto(campusService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<CampusDto> update(@PathVariable UUID id, @Valid @RequestBody CampusCreateDto campusCreateDto) {
        return ResponseEntity.ok(new CampusDto(campusService.update(id, campusCreateDto)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        campusService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
