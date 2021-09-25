package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "advisors")
@NoArgsConstructor
@Getter
@Setter
public class Advisor {
    @Id
    private UUID id;

    @OneToOne
    private User user;
    @OneToMany
    private List<Course> courses;

    public Advisor(User user, List<Course> courses) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.courses = courses;
    }
}
