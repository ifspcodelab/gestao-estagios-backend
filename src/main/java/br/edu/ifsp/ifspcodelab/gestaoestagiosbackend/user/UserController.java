package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.UserUpdatePasswordDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> index() {
        return ResponseEntity.ok(userService.findAll().stream().map(userMapper::to).collect(Collectors.toList()));
    }

    @GetMapping("{registration}")
    public ResponseEntity<UserDto> show(@PathVariable String registration) {
        return ResponseEntity.ok(userMapper.to(userService.findByRegistrationIgnoreCase(registration)));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable UUID id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userMapper.to(userService.updateUser(id, userUpdateDto)));
    }

    @PostMapping("{registration}")
    public ResponseEntity<UserDto> forgetPassword(@PathVariable String registration) {
        return ResponseEntity.ok(userMapper.to(userService.sendMailPassword(registration)));
    }

    @PatchMapping("{id}/redefine")
    public ResponseEntity<Void> redefinePassword(@PathVariable UUID id,
                                                 @Valid @RequestBody UserUpdatePasswordDto userUpdatePasswordDto) {
        userService.changePassword(id, userUpdatePasswordDto);
        return ResponseEntity.noContent().build();
    }
}
