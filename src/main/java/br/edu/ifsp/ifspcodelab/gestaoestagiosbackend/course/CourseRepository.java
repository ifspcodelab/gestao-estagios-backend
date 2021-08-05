package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, UUID> {
    boolean existsByNameAndDepartmentId(String name, UUID id);
}
