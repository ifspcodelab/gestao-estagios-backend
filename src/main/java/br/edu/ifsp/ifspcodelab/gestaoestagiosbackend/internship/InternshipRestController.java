package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class InternshipRestController {
    private final InternshipService internshipService;
    private final InternshipMapper internshipMapper;

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

    @PatchMapping("api/v1/internships/{internshipId}/update-status")
    public ResponseEntity<InternshipDto> updateInternshipStatus(@PathVariable UUID internshipId) {
        return ResponseEntity.ok(internshipMapper.to(internshipService.updateStatus(internshipId)));
    }

    @GetMapping("api/v1/internships/{internshipId}/final-documentation")
    public ResponseEntity<byte[]> finalDocumentation(@PathVariable UUID internshipId) {
        FinalDocumentationDto finalDocumentationDto = internshipService.generateFinalDocumentation(internshipId);

        byte[] bytes = finalDocumentationDto.getBytes();
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=" + finalDocumentationDto.getFileName())
            .contentLength(bytes.length)
            .contentType(MediaType.APPLICATION_PDF)
            .body(bytes);
    }
}
