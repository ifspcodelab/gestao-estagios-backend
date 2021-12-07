package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/internships/{internshipId}/realization-terms")
@AllArgsConstructor
public class RealizationTermRestController {
    private final RealizationTermService realizationTermService;

    @PostMapping
    public ResponseEntity<RealizationTerm> create(
        @PathVariable UUID internshipId,
        @RequestPart("file") MultipartFile file
    ) {
        RealizationTerm realizationTerm = realizationTermService.create(internshipId, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(realizationTerm.getId()).toUri();
        return ResponseEntity.created(uri).body(realizationTerm);
    }

    @PutMapping("{realizationTermId}")
    public ResponseEntity<RealizationTerm> update(
        @PathVariable UUID internshipId,
        @PathVariable UUID realizationTermId,
        @RequestBody @Valid RealizationTermUpdateDto realizationTermUpdateDto
    ) {
        return ResponseEntity.ok(
            realizationTermService.update(internshipId, realizationTermId, realizationTermUpdateDto)
        );
    }

    @PutMapping("{realizationTermId}/appraisal")
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
