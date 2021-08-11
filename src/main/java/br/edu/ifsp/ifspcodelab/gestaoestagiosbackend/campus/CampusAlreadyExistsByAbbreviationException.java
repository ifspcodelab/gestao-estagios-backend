package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Getter;

@Getter
public class CampusAlreadyExistsByAbbreviationException extends RuntimeException {
    private final String abbreviation;

    public CampusAlreadyExistsByAbbreviationException(String abbreviation) {
        super();
        this.abbreviation = abbreviation;
    }
}
