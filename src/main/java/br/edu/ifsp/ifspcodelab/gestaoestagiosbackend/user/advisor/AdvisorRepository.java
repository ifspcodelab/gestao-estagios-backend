package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, UUID> {
}
