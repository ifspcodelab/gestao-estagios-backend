package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cities")
@NoArgsConstructor
@Getter
@Setter
public class City {
    @Id
    private UUID id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

    public City(String name, State state) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.state = state;
    }
}
