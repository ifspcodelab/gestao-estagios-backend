package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course create(CourseCreateDto courseCreateDto);
    List<Course> findAll();
    Course findById(UUID courseId);
    Course update(UUID courseId, CourseCreateDto courseCreateDto);
    Course setStatus(UUID courseId, CourseUpdateStatusDto courseUpdateStatusDto);
    void delete(UUID courseId);
}
