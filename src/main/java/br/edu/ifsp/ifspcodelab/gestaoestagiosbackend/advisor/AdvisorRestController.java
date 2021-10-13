package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.UserUpdatePasswordDto;
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

    @GetMapping("{id}")
    public ResponseEntity<AdvisorDto> show(@PathVariable UUID id) {
        return ResponseEntity.ok(advisorMapper.to(advisorService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<AdvisorDto> update(@PathVariable UUID id,
                                             @Valid @RequestBody UserAdvisorUpdateDto userAdvisorUpdateDto) {
        return ResponseEntity.ok(advisorMapper.to(userService.updateAdvisor(id, userAdvisorUpdateDto)));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable UUID id, @RequestBody UserUpdatePasswordDto userUpdatePasswordDto) {
        userService.activateAdvisor(id, userUpdatePasswordDto);
        return ResponseEntity.noContent().build();
    }
}
