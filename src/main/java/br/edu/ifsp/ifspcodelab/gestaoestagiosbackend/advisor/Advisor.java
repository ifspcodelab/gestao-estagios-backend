package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
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
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Course> courses;

    @Enumerated(EnumType.STRING)
    private EntityStatus isActivated;

    public Advisor(User user, Set<Course> courses, EntityStatus status) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.courses = courses;
        this.isActivated = status;
    }
}
