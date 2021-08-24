package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CityRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CityRepository cityRepository;

    private State state;
    private City city;

    @BeforeEach
    public void setUp() {
        state = new State("TS", "Test State");
        city = new City("Test City", state);
    }

    @Test
    public void findAllByStateAbbreviation_returnOneCity() {
        State state = new State("TS", "Test State");
        City city = new City("City A", state);

        entityManager.persistAndFlush(state);
        entityManager.persistAndFlush(city);

        List<City> cities = cityRepository.findAllByStateAbbreviation(state.getAbbreviation());

        assertThat(cities).hasSize(1);
        assertThat(cities.get(0).getId()).isEqualTo(city.getId());
    }

    @Test
    public void findAllByStateAbbreviation_returnMoreThanOneCity() {
        State state = new State("TS", "Test State");
        City city = new City("City A", state);
        City city2 = new City("City B", state);

        entityManager.persistAndFlush(state);
        entityManager.persistAndFlush(city);
        entityManager.persistAndFlush(city2);

        List<City> cities = cityRepository.findAllByStateAbbreviation(state.getAbbreviation());

        assertThat(cities).hasSize(2);
        assertThat(cities.get(0).getId()).isEqualTo(city.getId());
        assertThat(cities.get(1).getId()).isEqualTo(city2.getId());
    }

    @Test
    public void findAllByStateAbbreviation_returnByState() {
        State state = new State("TS", "Test State");
        State state2 = new State("TB", "Test State 2");
        City city = new City("City A", state);
        City city2 = new City("City B", state2);

        entityManager.persistAndFlush(state);
        entityManager.persistAndFlush(state2);
        entityManager.persistAndFlush(city);
        entityManager.persistAndFlush(city2);

        List<City> cities = cityRepository.findAllByStateAbbreviation(state.getAbbreviation());

        assertThat(cities).hasSize(1);
        assertThat(cities.get(0).getId()).isEqualTo(city.getId());
    }

    @Test
    public void findAllByStateAbbreviation_returnEmptyList() {
        State state = new State("TS", "Test State");

        entityManager.persistAndFlush(state);

        List<City> cities = cityRepository.findAllByStateAbbreviation(state.getAbbreviation());

        assertThat(cities).isEmpty();
    }
}
