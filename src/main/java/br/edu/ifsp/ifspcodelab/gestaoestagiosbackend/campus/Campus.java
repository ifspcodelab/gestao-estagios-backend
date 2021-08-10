package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "campuses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Campus {
    @Id
    private UUID id;
    private String name;
    private String abbreviation;
    @Embedded
    private Address address;
    private String telephone;
    private String email;
    private String website;

    public Campus(String name, String abbreviation, Address address, String telephone, String email, String website) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.abbreviation = abbreviation;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.website = website;
    }
}
