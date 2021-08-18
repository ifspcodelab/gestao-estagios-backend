package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    boolean existsByAbbreviationAndDepartmentId(String abbreviation, UUID departmentId);
}
