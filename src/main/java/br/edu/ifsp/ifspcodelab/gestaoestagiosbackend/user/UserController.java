package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
