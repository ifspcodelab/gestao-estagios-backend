package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @Embedded
    private InternshipSector internshipSector;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    public Campus(String name, String abbreviation, Address address, InternshipSector internshipSector) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.abbreviation = abbreviation;
        this.address = address;
        this.internshipSector = internshipSector;
        this.status = EntityStatus.ENABLED;
    }
}
