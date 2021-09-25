package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserAdvisorCreateDto;

import java.util.List;

public interface AdvisorService {
    Advisor create(Advisor advisor);
    List<Course> getCourses(UserAdvisorCreateDto userAdvisorCreateDto);
}
