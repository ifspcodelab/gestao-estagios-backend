package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {
    private final String email;

    public UserAlreadyExistsException(String email) {
        super();
        this.email = email;
    }
}
