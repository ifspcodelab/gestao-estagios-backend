package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Getter;

@Getter
public class CampusAlreadyExistsException extends RuntimeException {
    private final String name;

    public CampusAlreadyExistsException(String name) {
        super();
        this.name = name;
    }
}
