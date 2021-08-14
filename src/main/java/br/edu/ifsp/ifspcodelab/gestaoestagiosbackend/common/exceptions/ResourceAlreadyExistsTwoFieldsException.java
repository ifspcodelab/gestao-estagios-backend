package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

@Getter
public class ResourceAlreadyExistsTwoFieldsException extends RuntimeException {
    private final ResourceName resourceName;
    private final String firstResourceField, secondResourceField;
    private final Object firstResourceValue, secondResourceValue;

    public ResourceAlreadyExistsTwoFieldsException(ResourceName resourceName, String firstResourceField, String secondResourceField, Object firstResourceValue, Object secondResourceValue) {
        super();
        this.resourceName = resourceName;
        this.firstResourceField = firstResourceField;
        this.secondResourceField = secondResourceField;
        this.firstResourceValue = firstResourceValue;
        this.secondResourceValue = secondResourceValue;
    }
}
