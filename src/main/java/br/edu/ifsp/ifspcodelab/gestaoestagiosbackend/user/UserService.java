package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;


import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;

import java.util.List;

public interface UserService {
    Advisor createAdvisor(UserAdvisorCreateDto userAdvisorCreateDto);
    List<User> findAll();
    Student createStudent(UserStudentCreateDto userStudentCreateDto);
}
