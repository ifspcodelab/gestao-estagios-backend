package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    private UUID id;
    private String name;
    private String abbreviation;
    private Integer numberOfPeriods;

    @ManyToOne
    private Department department;

    public Course(String name, String abbreviation, Integer numberOfPeriods, Department department) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.abbreviation = abbreviation;
        this.numberOfPeriods = numberOfPeriods;
        this.department = department;
    }
}
