package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampusServiceTest {
    @Mock
    private CampusRepository campusRepository;
    @InjectMocks
    private CampusServiceImpl campusService;

    private String name;
    private String abbreviation;
    private String postalCode;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String number;
    private String complement;
    private Address address;
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
        address = new Address(postalCode, street, neighborhood, city, state, number, complement);
        telephone = "1234-5678";
        email = "testcampus@email.com";
        website = "https://testcampus.com";
    }

    @Test
    public void createCampus() {
        when(campusRepository.save(any(Campus.class))).thenReturn(new Campus(
            name,
            abbreviation,
            address,
            telephone,
            email,
            website
        ));

        Campus campusCreated = campusService.create(new CampusCreateDto(
            name,
            abbreviation,
            new AddressDto(address),
            telephone,
            email,
            website
        ));

        verify(campusRepository, times(1)).save(any(Campus.class));

        assertThat(campusCreated).isNotNull();
        assertThat(campusCreated.getId()).isNotNull();
        assertThat(campusCreated.getName()).isEqualTo(name);
        assertThat(campusCreated.getAbbreviation()).isEqualTo(abbreviation);
        assertThat(campusCreated.getAddress()).isEqualTo(address);
        assertThat(campusCreated.getTelephone()).isEqualTo(telephone);
        assertThat(campusCreated.getEmail()).isEqualTo(email);
        assertThat(campusCreated.getWebsite()).isEqualTo(website);
    }

}
