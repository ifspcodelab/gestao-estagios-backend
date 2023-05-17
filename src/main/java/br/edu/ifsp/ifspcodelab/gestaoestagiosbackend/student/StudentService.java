package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    Student create(Student student);
    List<Student> findAll();
    Student findById(UUID id);
    Student findByUserId(UUID userId);
    UserDto update(UserDto userDto);
    void delete(UUID id);
    boolean existsByCurriculumId(UUID curriculumId);
}
