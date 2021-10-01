package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    private UUID id;

    @OneToOne
    private User user;

    @ManyToOne
    private Curriculum curriculum;

    public Student(User user, Curriculum curriculum) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.curriculum = curriculum;
    }
}
