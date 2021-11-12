package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InternshipRepository extends JpaRepository<Internship, UUID> {
    List<Internship> findAllByAdvisorRequestStudentId(UUID studentId);
    List<Internship> findAllByAdvisorRequestAdvisorId(UUID studentId);
}
