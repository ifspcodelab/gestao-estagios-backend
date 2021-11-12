package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
