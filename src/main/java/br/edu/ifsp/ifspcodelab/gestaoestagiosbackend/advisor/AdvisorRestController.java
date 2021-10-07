package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserAdvisorCreateDto;
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
@RequestMapping("api/v1/advisors")
@AllArgsConstructor
public class AdvisorRestController {
    private final UserService userService;
    private final AdvisorService advisorService;
    private final AdvisorMapper advisorMapper;

    @PostMapping()
    public ResponseEntity<AdvisorDto> create(@Valid @RequestBody UserAdvisorCreateDto userAdvisorCreateDto) {
        Advisor advisor = this.userService.createAdvisor(userAdvisorCreateDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/advisors").toUriString());
        return ResponseEntity.created(uri).body(advisorMapper.to(advisor));
    }

    @GetMapping()
    public ResponseEntity<List<AdvisorDto>> index() {
        return ResponseEntity.ok(advisorService.findAll().stream()
            .map(advisorMapper::to).
            collect(Collectors.toList()));
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activate(@PathVariable UUID id, @RequestBody String password) {
        userService.activateAdvisor(id, password);
        return ResponseEntity.noContent().build();
    }
}
