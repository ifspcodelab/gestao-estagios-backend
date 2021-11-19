package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RealizationTermRepository extends JpaRepository<RealizationTerm, UUID> {
}
