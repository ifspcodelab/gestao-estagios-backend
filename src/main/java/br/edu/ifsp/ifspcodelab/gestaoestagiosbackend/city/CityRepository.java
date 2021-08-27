package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    List<City> findAllByStateAbbreviation(String stateAbbreviation);
}
