package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CampusNotExistsException extends RuntimeException{
    private final UUID campusId;

    public CampusNotExistsException(UUID campusId){
        super();
        this.campusId = campusId;
    }
}
