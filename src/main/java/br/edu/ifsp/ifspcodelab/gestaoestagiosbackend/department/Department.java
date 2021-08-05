package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public Department(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
