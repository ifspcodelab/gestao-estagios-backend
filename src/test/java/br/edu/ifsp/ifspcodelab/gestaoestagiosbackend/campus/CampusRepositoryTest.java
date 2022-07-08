package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.StateFactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CampusRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CampusRepository campusRepository;
    private City city;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
    }

    @Test
    public void saveNewCampus() {
        Campus campus = CampusFactoryUtils.sampleCampus(city);

        campusRepository.save(campus);
        Campus campusFound = entityManager.find(Campus.class, campus.getId());

        assertThat(campusFound).isNotNull();
        assertThat(campusFound.getId()).isEqualTo(campus.getId());
        assertThat(campusFound.getName()).isEqualTo(campus.getName());
        assertThat(campusFound.getAbbreviation()).isEqualTo(campus.getAbbreviation());
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
    public void findAllByStatusShouldReturnOnlyEnabledCampusesWhenSpecifiedStatusIsEnabled() {
        Campus campusEnabled =  CampusFactoryUtils.sampleCampus(city);
        Campus campusEnabled2 = CampusFactoryUtils.sampleCampus(city);
        Campus campusDisabled = CampusFactoryUtils.sampleCampusDisabled(city);
        entityManager.persistAndFlush(campusEnabled);
        entityManager.persistAndFlush(campusEnabled2);
        entityManager.persistAndFlush(campusDisabled);

        List<Campus> campuses = campusRepository.findAllByStatus(EntityStatus.ENABLED);

        assertThat(campuses)
                .hasSize(2)
                .extracting(Campus::getId)
                .containsExactlyInAnyOrder(campusEnabled.getId(), campusEnabled2.getId());
    }

    @Test
    public void findAllByStatusShouldReturnEmptyListWhenThereIsNotCampusWithEnabledStatus() {
        Campus campusDisabled = CampusFactoryUtils.sampleCampusDisabled(city);
        entityManager.persistAndFlush(campusDisabled);

        List<Campus> campuses = campusRepository.findAllByStatus(EntityStatus.ENABLED);

        assertThat(campuses).isEmpty();
    }

    @Test
    public void findAllByStatusShouldReturnOnlyDisabledCampusesWhenSpecifiedStatusIsDisabled() {
        Campus campusDisabled = CampusFactoryUtils.sampleCampusDisabled(city);
        Campus campusDisabled2 = CampusFactoryUtils.sampleCampusDisabled(city);
        Campus campusEnabled = CampusFactoryUtils.sampleCampus(city);
        entityManager.persistAndFlush(campusDisabled);
        entityManager.persistAndFlush(campusDisabled2);
        entityManager.persistAndFlush(campusEnabled);

        List<Campus> campuses = campusRepository.findAllByStatus(EntityStatus.DISABLED);

        assertThat(campuses)
                .hasSize(2)
                .extracting(Campus::getId)
                .containsExactlyInAnyOrder(campusDisabled.getId(), campusDisabled2.getId());
    }

    @Test
    public void findAllByStatusShouldReturnEmptyListWhenThereIsNotCampusWithDisabledStatus() {
        Campus campusEnabled = CampusFactoryUtils.sampleCampus(city);
        entityManager.persistAndFlush(campusEnabled);

        List<Campus> campuses = campusRepository.findAllByStatus(EntityStatus.DISABLED);

        assertThat(campuses).isEmpty();
    }

    @Test
    public void existsByAbbreviationShouldReturnTrueWhenThereIsCampusWithSpecifiedAbbreviation() {
        Campus campus = CampusFactoryUtils.sampleCampus(city);
        entityManager.persistAndFlush(campus);

        boolean result = campusRepository.existsByAbbreviation("TCS");

        assertThat(result).isTrue();
    }

    @Test
    public void existsByAbbreviationShouldReturnFalseWhenThereIsNotCampusWithSpecifiedAbbreviation() {
        Campus campus = CampusFactoryUtils.sampleCampus(city);
        entityManager.persistAndFlush(campus);

        boolean result = campusRepository.existsByAbbreviation("SPO");

        assertThat(result).isFalse();
    }
}
