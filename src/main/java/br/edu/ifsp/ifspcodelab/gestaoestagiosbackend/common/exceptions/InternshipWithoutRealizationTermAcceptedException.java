package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class InternshipWithoutRealizationTermAcceptedException extends RuntimeException {
    private UUID internshipId;

    public InternshipWithoutRealizationTermAcceptedException(UUID internshipId) {
        super();
        this.internshipId = internshipId;
    }
}
