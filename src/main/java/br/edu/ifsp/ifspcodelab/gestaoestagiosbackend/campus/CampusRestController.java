package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ProblemDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final CampusMapper campusMapper;

    @Operation(summary = "Create a new campus")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", description = "OK",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CampusDto.class))}
        ),
        @ApiResponse(
            responseCode = "400", description = "Bad Request",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}
        )
    })
    @PostMapping
    public ResponseEntity<CampusDto> create(@Valid @RequestBody CampusCreateDto campusCreateDto) {
        Campus campus = campusService.create(campusCreateDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(campus.getId()).toUri();
        return ResponseEntity.created(uri).body(campusMapper.to(campus));
    }

    @GetMapping
    public ResponseEntity<List<CampusDto>> index() {
        List<CampusDto> campuses = campusService.findAll().stream()
            .map(campusMapper::to)
            .collect(Collectors.toList());
        return ResponseEntity.ok(campuses);
    }

    @GetMapping("{id}")
    public ResponseEntity<CampusDto> show(@PathVariable UUID id) {
        return ResponseEntity.ok(campusMapper.to(campusService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<CampusDto> update(@PathVariable UUID id, @Valid @RequestBody CampusCreateDto campusCreateDto) {
        return ResponseEntity.ok(campusMapper.to(campusService.update(id, campusCreateDto)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        campusService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<CampusDto> patch(
        @PathVariable UUID id,
        @Valid @RequestBody EntityUpdateStatusDto campusUpdateStatusDto)
    {
        return ResponseEntity.ok(campusMapper.to(campusService.setStatus(id, campusUpdateStatusDto)));
    }
}
