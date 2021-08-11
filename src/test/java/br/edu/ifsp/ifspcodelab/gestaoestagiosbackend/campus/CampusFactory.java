package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

public class CampusFactory {
    public static Campus sampleCampus() {
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
        InternshipSector internshipSector = new InternshipSector(telephone, email, website);

        return new Campus(name, abbreviation, address, internshipSector);
    }
}
