package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/advisor-requests")
@AllArgsConstructor
public class AdvisorRequestRestController {
    private final AdvisorRequestService advisorRequestService;

    @PostMapping()
    public ResponseEntity<AdvisorRequest> create(@RequestBody AdvisorRequestCreateDto advisorRequestCreateDto) {
        AdvisorRequest advisorRequest = this.advisorRequestService.create(advisorRequestCreateDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/advisor-requests").toUriString());
        return ResponseEntity.created(uri).body(advisorRequest);
    }

    @GetMapping("/advisors/{id}")
    public ResponseEntity<List<AdvisorRequest>> showByAdvisorId(@PathVariable UUID id) {
        List<AdvisorRequest> advisorRequestsList = this.advisorRequestService.findByAdvisorId(id);
        return ResponseEntity.ok(advisorRequestsList);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<AdvisorRequestForStudentDto>> showByStudentId(@PathVariable UUID id) {
        List<AdvisorRequestForStudentDto> advisorRequestsList = this.advisorRequestService.findByStudentId(id);
        return ResponseEntity.ok(advisorRequestsList);
    }
}
