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
@NoArgsConstructor
@Setter


public class Campus {


    @Id
    private UUID id;
    private String name;
    private String abbreviation;
    private String email;
    private String telephone;
    private String siteSector;


 public Campus(String name, String abbreviation, String email, String telephone, String siteSector) {

  this.id = UUID.randomUUID();
  this.abbreviation = abbreviation;
  this.email = email;
  this.telephone = telephone;
  this.siteSector = siteSector;
  this.name = name;
 }


}
