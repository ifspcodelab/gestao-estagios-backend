package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "states")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class State {
    @Id
    private String abbreviation;
    private String name;
}
