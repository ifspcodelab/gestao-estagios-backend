package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student create(Student student);
    List<Student> findAll();
}
