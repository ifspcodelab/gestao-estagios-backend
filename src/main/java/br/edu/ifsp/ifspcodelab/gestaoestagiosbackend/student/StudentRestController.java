package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserStudentCreateDto;
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
public class StudentRestController {
    private final UserService userService;
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @PostMapping("api/v1/students")
    public ResponseEntity<StudentDto> create(@Valid @RequestBody UserStudentCreateDto userStudentCreateDto) {
        Student student = this.userService.createStudent(userStudentCreateDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/api/students").toUriString());
        return ResponseEntity.created(uri).body(studentMapper.to(student));
    }

    @GetMapping("api/v1/students")
    public ResponseEntity<List<StudentDto>> index() {
        return ResponseEntity.ok(studentService.findAll().stream()
                .map(studentMapper::to).
                collect(Collectors.toList()));
    }

    @GetMapping("api/v1/students/{id}")
    public ResponseEntity<StudentDto> show(@PathVariable UUID id) {
        return ResponseEntity.ok(studentMapper.to(studentService.findById(id)));
    }

    @GetMapping("api/v1/users/{id}/students")
    public ResponseEntity<StudentDto> showByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(studentMapper.to(studentService.findByUserId(id)));
    }

    @PutMapping("api/v1/students")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(studentService.update(userDto));
    }

    @DeleteMapping("api/v1/students/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("api/v1/students/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable UUID id) {
        userService.activateStudent(id);
        return ResponseEntity.noContent().build();
    }
}
