package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ProblemDetail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateDto userCreateDto) {
        User user = this.userService.create(userCreateDto.getEmail(), userCreateDto.getPassword());
        return new ResponseEntity<>(new UserDto(user.getId(), user.getEmail()), HttpStatus.CREATED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> alreadyExists(UserAlreadyExistsException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                "User already exists",
                List.of(new Violation("email", "User already exists with email " + exception.getEmail()))
            ),
            HttpStatus.CONFLICT
        );
    }
}
