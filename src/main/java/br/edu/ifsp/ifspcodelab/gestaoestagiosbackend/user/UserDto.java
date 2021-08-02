package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import java.util.UUID;

public class UserDto {
    private UUID id;
    private String email;

    public UserDto(UUID id, String email) {
        this.id = id;
        this.email = email;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
