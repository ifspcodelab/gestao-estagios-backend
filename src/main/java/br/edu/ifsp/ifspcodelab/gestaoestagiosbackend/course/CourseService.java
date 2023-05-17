package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course create(CourseCreateDto courseCreateDto);
    List<Course> findAll(Sort sortOrder);
    List<Course> findAllByStatus(EntityStatus status);
    Course findById(UUID courseId);
    Course update(UUID courseId, CourseCreateDto courseCreateDto);
    Course setStatus(UUID courseId, EntityUpdateStatusDto courseUpdateStatusDto);
    void delete(UUID courseId);
    void disableAllByDepartmentId(UUID departmentId);
    boolean existsByDepartmentId(UUID departmentId);
    List<Course> findAllByDepartmentId(UUID departmentId);
    Course enable(UUID courseId);
    List<Course> findByIdIn(List<UUID> coursesIds);
}
