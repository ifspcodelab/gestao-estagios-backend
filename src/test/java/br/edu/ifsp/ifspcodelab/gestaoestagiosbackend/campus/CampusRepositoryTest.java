package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.StateFactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CampusRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CampusRepository campusRepository;

    private Campus campus;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        campus = CampusFactoryUtils.sampleCampus(city);
    }

    @Test
    public void saveNewCampus() {
        entityManager.persistAndFlush(campus);
        Campus campusFound = entityManager.find(Campus.class, campus.getId());

        assertThat(campusFound).isNotNull();
        assertThat(campusFound.getId()).isEqualTo(campus.getId());
        assertThat(campusFound.getName()).isEqualTo(campus.getName());
        assertThat(campusFound.getAbbreviation()).isEqualTo(campus.getAbbreviation());
        assertThat(campusFound.getInitialRegistrationPattern()).isEqualTo(campus.getInitialRegistrationPattern());
        assertThat(campusFound.getAddress().getPostalCode()).isEqualTo(campus.getAddress().getPostalCode());
        assertThat(campusFound.getAddress().getStreet()).isEqualTo(campus.getAddress().getStreet());
        assertThat(campusFound.getAddress().getNeighborhood()).isEqualTo(campus.getAddress().getNeighborhood());
        assertThat(campusFound.getAddress().getCity()).isEqualTo(campus.getAddress().getCity());
        assertThat(campusFound.getAddress().getNumber()).isEqualTo(campus.getAddress().getNumber());
        assertThat(campusFound.getAddress().getComplement()).isEqualTo(campus.getAddress().getComplement());
        assertThat(campusFound.getInternshipSector().getTelephone()).isEqualTo(campus.getInternshipSector().getTelephone());
        assertThat(campusFound.getInternshipSector().getEmail()).isEqualTo(campus.getInternshipSector().getEmail());
        assertThat(campusFound.getInternshipSector().getWebsite()).isEqualTo(campus.getInternshipSector().getWebsite());
    }

    @Test
    public void campusExistsByInitialRegistrationPattern(){
        entityManager.persistAndFlush(campus);

        boolean result = campusRepository.existsByInitialRegistrationPattern("SP");

        assertThat(result).isTrue();
    }

    @Test
    public void campusNotExistsByInitialRegistrationPattern(){
        entityManager.persistAndFlush(campus);

        boolean result = campusRepository.existsByInitialRegistrationPattern("SU");

        assertThat(result).isFalse();
    }

    @Test
    public void campusExistsByInternshipSectorEmail(){
        entityManager.persistAndFlush(campus);

        boolean result = campusRepository.existsByInternshipSectorEmail("testcampus@email.com");

        assertThat(result).isTrue();
    }

    @Test
    public void campusNotExistsByInternshipSectorEmail(){
        entityManager.persistAndFlush(campus);

        boolean result = campusRepository.existsByInitialRegistrationPattern("test22campus@email.com");

        assertThat(result).isFalse();
    }
}
