package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

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
@CrossOrigin
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
        @PathVariable UUID courseId
    ) {
        return ResponseEntity.ok(curriculumService.findAll(courseId)
            .stream()
            .map(c -> new CurriculumDto(
                c.getId(),
                c.getCode(),
                c.getCourseLoad(),
                c.getInternshipCourseLoad(),
                c.getInternshipStartCriteria(),
                c.getInternshipAllowedActivities(),
                c.getStatus()
            )).collect(Collectors.toList())
        );
    }
}
