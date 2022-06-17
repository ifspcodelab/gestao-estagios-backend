package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
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
@RequestMapping("api/v1/courses/{courseId}/curriculums")
@AllArgsConstructor
public class CurriculumRestController {
    private CurriculumService curriculumService;
    private CurriculumMapper curriculumMapper;

    @PostMapping
    public ResponseEntity<CurriculumDto> create(
        @PathVariable UUID courseId,
        @Valid @RequestBody CurriculumCreateDto curriculumCreateDto
    ) {
        Curriculum curriculum = curriculumService.create(courseId, curriculumCreateDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{curriculumId}")
            .buildAndExpand(curriculum.getId()).toUri();
        return ResponseEntity.created(uri).body(curriculumMapper.to(curriculum));
    }

    @GetMapping
    public ResponseEntity<List<CurriculumDto>> index(
        @PathVariable UUID courseId,
        @RequestParam(required = false) EntityStatus status
    ) {
        if (status != null)
        {
            return ResponseEntity.ok(curriculumService.findByStatus(courseId, status)
                    .stream()
                    .map(curriculumMapper::to)
                    .collect(Collectors.toList())
            );
        }

        return ResponseEntity.ok(curriculumService.findAll(courseId)
            .stream()
            .map(curriculumMapper::to)
            .collect(Collectors.toList())
        );
    }

    @GetMapping("{curriculumId}")
    public ResponseEntity<CurriculumDto> show(
        @PathVariable UUID courseId,
        @PathVariable UUID curriculumId
    ) {
        return ResponseEntity.ok(curriculumMapper.to(curriculumService.findById(courseId, curriculumId)));
    }

    @PutMapping("{curriculumId}")
    public ResponseEntity<CurriculumDto> update(
        @PathVariable UUID courseId,
        @PathVariable UUID curriculumId,
        @Valid @RequestBody CurriculumCreateDto curriculumCreateDto
    ) {
        return ResponseEntity.ok(curriculumMapper.to(
            curriculumService.update(courseId, curriculumId, curriculumCreateDto)
        ));
    }

    @DeleteMapping("{curriculumId}")
    public ResponseEntity<Void> delete(
        @PathVariable UUID courseId,
        @PathVariable UUID curriculumId
    ) {
        curriculumService.delete(courseId, curriculumId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{curriculumId}")
    public ResponseEntity<CurriculumDto> patch(
        @PathVariable UUID courseId,
        @PathVariable UUID curriculumId,
        @Valid @RequestBody EntityUpdateStatusDto curriculumUpdateStatusDto
    ) {
        return ResponseEntity.ok(curriculumMapper.to(
            curriculumService.setStatus(courseId, curriculumId, curriculumUpdateStatusDto)
        ));
    }
}
