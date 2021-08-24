package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    List<City> findAllByStateAbbreviation(String stateAbbreviation);
}
