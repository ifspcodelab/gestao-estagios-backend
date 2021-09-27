package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserAdvisorCreateDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/advisors")
@AllArgsConstructor
public class AdvisorRestController {
    private final UserService userService;
    private final AdvisorMapper advisorMapper;

    @PostMapping()
    public ResponseEntity<AdvisorDto> create(@Valid @RequestBody UserAdvisorCreateDto userAdvisorCreateDto) {
        Advisor advisor = this.userService.createAdvisor(userAdvisorCreateDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(advisorMapper.to(advisor));
    }
}
