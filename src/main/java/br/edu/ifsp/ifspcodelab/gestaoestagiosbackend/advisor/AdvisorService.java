package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;

import java.util.List;
import java.util.UUID;

public interface AdvisorService {
    Advisor create(Advisor advisor);
    List<Advisor> findAll();
    List<Course> getCourses(List<UUID> coursesIds);
}
