package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@Getter
@Setter
public class Department {
    @Id
    private UUID id;
    private String name;
    private String abbreviation;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @ManyToOne
    private Campus campus;

    public Department(String name, String abbreviation, Campus campus) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.abbreviation = abbreviation;
        this.status = EntityStatus.ENABLED;
        this.campus = campus;
    }
}
