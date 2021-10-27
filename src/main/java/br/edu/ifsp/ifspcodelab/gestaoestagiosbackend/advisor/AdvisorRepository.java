package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, UUID> {
    List<Advisor> findAllByCoursesId(UUID id);
    Optional<Advisor> findByUserId(UUID userId);
}
