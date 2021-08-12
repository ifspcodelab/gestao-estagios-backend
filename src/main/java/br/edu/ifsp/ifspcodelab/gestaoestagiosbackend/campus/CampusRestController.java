package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(campus.getId()).toUri();
        return ResponseEntity.created(uri).body(new CampusDto(campus));
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
        return ResponseEntity.noContent().build();
    }
}
