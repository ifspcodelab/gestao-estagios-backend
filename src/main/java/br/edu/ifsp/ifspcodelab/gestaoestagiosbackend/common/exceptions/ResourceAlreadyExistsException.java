package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

@Getter
public class ResourceAlreadyExistsException extends RuntimeException {
    private final ResourceName resourceName;
    private final String resourceField;
    private final Object resourceValue;

    public ResourceAlreadyExistsException(ResourceName resourceName, String resourceField, Object resourceValue) {
        super();
        this.resourceName = resourceName;
        this.resourceField = resourceField;
        this.resourceValue = resourceValue;
    }
}
