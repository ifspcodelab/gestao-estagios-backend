package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm.RealizationTerm;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm.RealizationTermAppraisalDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm.RealizationTermService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm.RealizationTermUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class InternshipRestController {
    private final InternshipService internshipService;
    private final InternshipMapper internshipMapper;
    private final RealizationTermService realizationTermService;

    @GetMapping("api/v1/students/{studentId}/internships")
    public ResponseEntity<List<InternshipDto>> indexByStudentId(@PathVariable UUID studentId) {
        List<InternshipDto> internships = internshipService.findAllByAdvisorRequestStudentId(studentId)
            .stream()
            .map(internshipMapper::to)
            .collect(Collectors.toList());
        return ResponseEntity.ok(internships);
    }

    @GetMapping("api/v1/advisors/{advisorId}/internships")
    public ResponseEntity<List<InternshipDto>> indexByAdvisorId(@PathVariable UUID advisorId) {
        List<InternshipDto> internships = internshipService.findAllByAdvisorRequestAdvisorId(advisorId)
            .stream()
            .map(internshipMapper::to)
            .collect(Collectors.toList());
        return ResponseEntity.ok(internships);
    }

    @GetMapping("api/v1/internships/{internshipId}")
    public ResponseEntity<InternshipDto> show(@PathVariable UUID internshipId) {
        return ResponseEntity.ok(internshipMapper.to(internshipService.findById(internshipId)));
    }

    @PostMapping("api/v1/internships/{internshipId}/realization-terms")
    public ResponseEntity<RealizationTerm> create(
            @PathVariable UUID internshipId,
            @RequestPart("file") MultipartFile file
    ) {
        RealizationTerm realizationTerm = realizationTermService.create(internshipId, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(realizationTerm.getId()).toUri();
        return ResponseEntity.created(uri).body(realizationTerm);
    }

    @PutMapping("api/v1/internships/{internshipId}/realization-terms/{realizationTermId}")
    public ResponseEntity<RealizationTerm> update(
            @PathVariable UUID internshipId,
            @PathVariable UUID realizationTermId,
            @RequestBody @Valid RealizationTermUpdateDto realizationTermUpdateDto
            ) {
        return ResponseEntity.ok(
                realizationTermService.update(internshipId, realizationTermId, realizationTermUpdateDto)
        );
    }

    @PutMapping("api/v1/internships/{internshipId}/realization-terms/{realizationTermId}/appraisal")
    public ResponseEntity<RealizationTerm> appraisal(
            @PathVariable UUID internshipId,
            @PathVariable UUID realizationTermId,
            @RequestBody @Valid RealizationTermAppraisalDto realizationTermAppraisalDto
            ) {
        return ResponseEntity.ok(
                realizationTermService.appraisal(internshipId, realizationTermId, realizationTermAppraisalDto)
        );
    }
}
