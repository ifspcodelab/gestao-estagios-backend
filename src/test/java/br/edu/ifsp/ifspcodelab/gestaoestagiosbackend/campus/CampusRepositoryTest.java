package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

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

    private String name;
    private String abbreviation;
    private String postalCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String number;
    private String complement;
    private String telephone;
    private String email;
    private String website;

    @BeforeEach
    public void setUp() {
        name = "Test Campus";
        abbreviation = "TCS";
        postalCode = "123456";
        street = "Test Street";
        neighborhood = "Test Neighborhood";
        city = "Test City";
        state = "Test State";
        number = "123456";
        complement = "Test Complement";
        telephone = "1234-5678";
        email = "testcampus@email.com";
        website = "https://testcampus.com";
    }

    @Test
    public void saveNewCampus() {
        Address address = new Address(postalCode, street, neighborhood, city, state, number, complement);
        Campus campus = new Campus(name, abbreviation, address, telephone, email, website);
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
        assertThat(campusFound.getAddress().getState()).isEqualTo(campus.getAddress().getState());
        assertThat(campusFound.getAddress().getNumber()).isEqualTo(campus.getAddress().getNumber());
        assertThat(campusFound.getAddress().getComplement()).isEqualTo(campus.getAddress().getComplement());
        assertThat(campusFound.getTelephone()).isEqualTo(campus.getTelephone());
        assertThat(campusFound.getEmail()).isEqualTo(campus.getEmail());
        assertThat(campusFound.getWebsite()).isEqualTo(campus.getWebsite());
    }
}
