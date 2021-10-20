package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/advisor-requests")
@AllArgsConstructor
public class AdvisorRequestRestController {
    private final AdvisorRequestService advisorRequestService;

    private final AdvisorRequestForStudentMapper advisorRequestForStudentMapper;
    private final AdvisorRequestForAdvisorMapper advisorRequestForAdvisorMapper;

    @PostMapping()
    public ResponseEntity<AdvisorRequestForStudentDto> create(@RequestBody AdvisorRequestCreateDto advisorRequestCreateDto) {
        AdvisorRequest advisorRequest = this.advisorRequestService.create(advisorRequestCreateDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/advisor-requests").toUriString());
        return ResponseEntity.created(uri).body(advisorRequestForStudentMapper.to(advisorRequest));
    }

    @GetMapping("/advisors/{id}")
    public ResponseEntity<List<AdvisorRequestForAdvisorDto>> showByAdvisorId(@PathVariable UUID id) {
        List<AdvisorRequest> advisorRequestsList = this.advisorRequestService.findByAdvisorId(id);
        return ResponseEntity.ok(advisorRequestsList.stream().map(advisorRequestForAdvisorMapper::to).collect(Collectors.toList()));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<AdvisorRequestForStudentDto>> showByStudentId(@PathVariable UUID id) {
        List<AdvisorRequest> advisorRequestsList = this.advisorRequestService.findByStudentId(id);
        return ResponseEntity.ok(advisorRequestsList.stream().map(advisorRequestForStudentMapper::to).collect(Collectors.toList()));
    }
}
