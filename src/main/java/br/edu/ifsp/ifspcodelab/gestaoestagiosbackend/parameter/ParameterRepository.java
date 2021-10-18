package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParameterRepository extends JpaRepository<Parameter, UUID> {

}
