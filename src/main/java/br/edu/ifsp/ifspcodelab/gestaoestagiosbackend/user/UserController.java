package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

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

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserCreateDto userCreateDto) {
        User user = this.userService.create(userCreateDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(user);
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
