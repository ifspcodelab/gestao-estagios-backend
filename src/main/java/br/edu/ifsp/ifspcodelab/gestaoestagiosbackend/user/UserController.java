package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor.AdvisorDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor.AdvisorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AdvisorMapper advisorMapper;

    @PostMapping("advisors")
    public ResponseEntity<AdvisorDto> create(@Valid @RequestBody UserAdvisorCreateDto userAdvisorCreateDto) {
        Advisor advisor = this.userService.createAdvisor(userAdvisorCreateDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(advisorMapper.to(advisor));
    }

    @GetMapping
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.ok(userService.findAll());
    }

//    @ExceptionHandler(UserAlreadyExistsException.class)
//    public ResponseEntity<ProblemDetail> alreadyExists(UserAlreadyExistsException exception) {
//        return new ResponseEntity<>(
//            new ProblemDetail(
//                "User already exists",
//                List.of(new Violation("email", "User already exists with email " + exception.getEmail()))
//            ),
//            HttpStatus.CONFLICT
//        );
//    }
}
