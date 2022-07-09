package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;

public class CampusFactoryUtils {
    public static Campus sampleCampus(City city) {
        String name = "Test Campus";
        String abbreviation = "TCS";
        String initialRegistrationPattern = "SP";
        String postalCode = "123456";
        String street = "Test Street";
        String neighborhood = "Test Neighborhood";
        String number = "123456";
        String complement = "Test Complement";
        Address address = new Address(postalCode, street, neighborhood, number, complement, city);
        String telephone = "1234-5678";
        String email = "testcampus@email.com";
        String website = "https://testcampus.com";
        InternshipSector internshipSector = new InternshipSector(telephone, email, website);

        return new Campus(name, abbreviation, initialRegistrationPattern, address, internshipSector);
    }

    public static Campus sampleCampusEnabled(City city) {
        String name = "Test Campus";
        String abbreviation = "TCS";
        String initialRegistrationPattern = "SP";
        String postalCode = "123456";
        String street = "Test Street";
        String neighborhood = "Test Neighborhood";
        String number = "123456";
        String complement = "Test Complement";
        Address address = new Address(postalCode, street, neighborhood, number, complement, city);
        String telephone = "1234-5678";
        String email = "testcampus@email.com";
        String website = "https://testcampus.com";
        InternshipSector internshipSector = new InternshipSector(telephone, email, website);

        return new Campus(name, abbreviation, initialRegistrationPattern, address, internshipSector);
    }

    public static Campus sampleCampusDisabled(City city) {
        String name = "Test Campus";
        String abbreviation = "TCS";
        String initialRegistrationPattern = "SP";
        String postalCode = "123456";
        String street = "Test Street";
        String neighborhood = "Test Neighborhood";
        String number = "123456";
        String complement = "Test Complement";
        Address address = new Address(postalCode, street, neighborhood, number, complement, city);
        String telephone = "1234-5678";
        String email = "testcampus@email.com";
        String website = "https://testcampus.com";
        InternshipSector internshipSector = new InternshipSector(telephone, email, website);

        Campus campusDisabled =  new Campus(name, abbreviation, initialRegistrationPattern, address, internshipSector);
        campusDisabled.setStatus(EntityStatus.DISABLED);

        return campusDisabled;
    }
}
