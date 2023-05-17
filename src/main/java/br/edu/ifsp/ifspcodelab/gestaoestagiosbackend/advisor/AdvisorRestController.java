package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserAdvisorCreateDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserAdvisorUpdateDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserService;
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
@AllArgsConstructor
public class AdvisorRestController {
    private final UserService userService;
    private final AdvisorService advisorService;
    private final AdvisorMapper advisorMapper;

    @PostMapping("api/v1/advisors")
    public ResponseEntity<AdvisorDto> create(@Valid @RequestBody UserAdvisorCreateDto userAdvisorCreateDto) {
        Advisor advisor = this.userService.createAdvisor(userAdvisorCreateDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/advisors").toUriString());
        return ResponseEntity.created(uri).body(advisorMapper.to(advisor));
    }

    @GetMapping("api/v1/advisors")
    public ResponseEntity<List<AdvisorDto>> index() {
       List<Advisor> enabledAdvisors = advisorService.findAll()
               .stream()
               .filter(advisor -> advisor.getIsActivated() == EntityStatus.ENABLED)
               .collect(Collectors.toList());

        List<AdvisorDto> advisorDtos = enabledAdvisors.stream()
                .map(advisorMapper::to)
                .collect(Collectors.toList());

        return ResponseEntity.ok(advisorDtos);
    }

    @GetMapping("api/v1/courses/{id}/advisors")
    public ResponseEntity<List<AdvisorDto>> indexByCourseId(@PathVariable UUID id) {
        return ResponseEntity.ok(advisorService.findAllByCourseId(id)
            .stream()
            .map(advisorMapper::to)
            .collect(Collectors.toList()));
    }

    @GetMapping("api/v1/advisors/{id}")
    public ResponseEntity<AdvisorDto> show(@PathVariable UUID id) {
        return ResponseEntity.ok(advisorMapper.to(advisorService.findById(id)));
    }

    @GetMapping("api/v1/users/{id}/advisors")
    public ResponseEntity<AdvisorDto> showByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(advisorMapper.to(advisorService.findByUserId(id)));
    }

    @PutMapping("api/v1/advisors/{id}")
    public ResponseEntity<AdvisorDto> update(@PathVariable UUID id,
                                             @Valid @RequestBody UserAdvisorUpdateDto userAdvisorUpdateDto) {
        return ResponseEntity.ok(advisorMapper.to(userService.updateAdvisor(id, userAdvisorUpdateDto)));
    }

    @PatchMapping("api/v1/advisors/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable UUID id) {
        advisorService.activateAdvisor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("api/v1/advisors/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        advisorService.deactivateAdvisor(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("api/v1/advisors/{id}")
    public ResponseEntity<AdvisorDto> patch(@PathVariable UUID id,
                                            @Valid @RequestBody EntityUpdateStatusDto advisorUpdateStatusDto) {
        return ResponseEntity.ok(advisorMapper.to(advisorService.setStatus(id, advisorUpdateStatusDto)));
    }
}