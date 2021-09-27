package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

}
