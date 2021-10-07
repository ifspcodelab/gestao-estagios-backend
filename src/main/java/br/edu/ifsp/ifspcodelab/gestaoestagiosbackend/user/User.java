package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private UUID id;
    private String registration;
    private String name;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private EntityStatus isActivated;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Collection<Role> roles = new ArrayList<>();

    public User(String registration, String name, String password, String email, Collection<Role> roles) {
        this.id = UUID.randomUUID();
        this.registration = registration;
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.isActivated = EntityStatus.DISABLED;
    }

    public User(String registration, String name, String email, Collection<Role> roles) {
        this.id = UUID.randomUUID();
        this.registration = registration;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.isActivated = EntityStatus.DISABLED;
    }
}
