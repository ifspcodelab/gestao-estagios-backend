package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserStudentCreateDto;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    Student create(Student student);
    List<Student> findAll();
    Student findById(UUID id);
    UserDto update(UserDto userDto);
    void delete(UUID id);
}
