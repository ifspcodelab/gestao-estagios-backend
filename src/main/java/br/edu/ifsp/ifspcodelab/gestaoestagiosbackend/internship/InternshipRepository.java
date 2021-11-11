package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InternshipRepository extends JpaRepository<Internship, UUID> {
}
