package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Getter;

@Getter
public class CampusAlreadyExistsByEmailException extends RuntimeException {
    private final String email;

    public CampusAlreadyExistsByEmailException(String abbreviation) {
        super();
        this.email = abbreviation;
    }
}
