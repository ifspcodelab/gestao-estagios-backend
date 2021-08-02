package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private UUID id;
    private String email;
    private String password;

    public User(String email, String password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
    }
}
