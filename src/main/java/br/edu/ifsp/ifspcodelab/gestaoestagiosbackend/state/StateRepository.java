package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StateRepository extends JpaRepository<State, UUID> {
}
