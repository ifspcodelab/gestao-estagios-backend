package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    boolean existsByAbbreviationAndDepartmentId(String abbreviation, UUID departmentId);
    @Query("select count(c) > 0 from Course c join Department as d on c.department = d.id where c.abbreviation = ?1 and d.id = ?2 and c.id <> ?3")
    boolean existsByAbbreviationAndDepartmentIdExcludedId(String abbreviation, UUID departmentId, UUID courseId);
}
