package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "campus")
@Getter
@Setter
@NoArgsConstructor
public class Campus {
    @Id
    private UUID id;
    private String name;

    public Campus(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
