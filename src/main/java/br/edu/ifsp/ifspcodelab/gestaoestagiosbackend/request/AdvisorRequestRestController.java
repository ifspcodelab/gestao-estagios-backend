package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AdvisorRequestRestController {
    private final AdvisorRequestService advisorRequestService;

    private final AdvisorRequestForStudentMapper advisorRequestForStudentMapper;
    private final AdvisorRequestForAdvisorMapper advisorRequestForAdvisorMapper;

    @PostMapping("api/v1/advisor-requests")
    public ResponseEntity<AdvisorRequestForStudentDto> create(@RequestBody AdvisorRequestCreateDto advisorRequestCreateDto) {
        AdvisorRequest advisorRequest = this.advisorRequestService.create(advisorRequestCreateDto);
        return ResponseEntity.created(getURIFromAdvisorRequest(advisorRequest)).body(advisorRequestForStudentMapper.to(advisorRequest));
    }

    @GetMapping("api/v1/advisor-requests/{id}")
    public ResponseEntity<AdvisorRequestForAdvisorDto> show(@PathVariable UUID id) {
        return ResponseEntity.ok(advisorRequestForAdvisorMapper.to(advisorRequestService.findById(id)));
    }

    @GetMapping("api/v1/advisors/{id}/advisor-requests")
    public ResponseEntity<List<AdvisorRequestForAdvisorDto>> showByAdvisorId(@PathVariable UUID id) {
        List<AdvisorRequest> advisorRequestsList = this.advisorRequestService.findByAdvisorId(id);
        return ResponseEntity.ok(advisorRequestsList.stream().map(advisorRequestForAdvisorMapper::to).collect(Collectors.toList()));
    }

    @GetMapping("api/v1/students/{id}/advisor-requests")
    public ResponseEntity<List<AdvisorRequestForStudentDto>> showByStudentId(@PathVariable UUID id) {
        List<AdvisorRequest> advisorRequestsList = this.advisorRequestService.findByStudentId(id);
        return ResponseEntity.ok(advisorRequestsList.stream().map(advisorRequestForStudentMapper::to).collect(Collectors.toList()));
    }

    private URI getURIFromAdvisorRequest(AdvisorRequest advisorRequest) {
        Map<String, String> value = new HashMap<>();
        value.put("id", advisorRequest.getStudent().getId().toString());
        value.put("idAdvisorRequest", advisorRequest.getId().toString());
        return URI.create(ServletUriComponentsBuilder.fromPath("api/v1/students/{id}/advisor-requests/{idAdvisorRequest}").buildAndExpand(value).toUriString());
    }
}
