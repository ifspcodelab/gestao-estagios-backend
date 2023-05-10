package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByUserId(UUID userId);
    boolean existsByCurriculumId(UUID curriculumId);
}
