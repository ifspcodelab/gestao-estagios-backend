package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AdvisorService {
    Advisor create(Advisor advisor);
    List<Advisor> findAll();
    List<Advisor> findAllByCourseId(UUID courseId);
    Advisor findById(UUID id);
    Advisor findByUserId(UUID userId);
    Set<Course> getCourses(List<UUID> coursesIds);
    Advisor setStatus(UUID id, EntityUpdateStatusDto advisorUpdateStatusDto);
}
