package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CampusTest {

    @Test
    public void shouldGenerateIdWhenParameterizedConstructorIsCalled() {
        String name = "Test Campus";
        String abbreviation = "TCS";
        String postalCode = "123456";
        String street = "Test Street";
        String neighborhood = "Test Neighborhood";
        String city = "Test City";
        String state = "Test State";
        String number = "123456";
        String complement = "Test Complement";
        Address address = new Address(postalCode, street, neighborhood, city, state, number, complement);
        String telephone = "1234-5678";
        String email = "testcampus@email.com";
        String website = "https://testcampus.com";

        Campus campus = new Campus(name, abbreviation, address, telephone, email, website);

        assertThat(campus.getId()).isNotNull();
    }
}
