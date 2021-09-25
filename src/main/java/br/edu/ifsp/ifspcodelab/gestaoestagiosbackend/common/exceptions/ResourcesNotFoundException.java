package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ResourcesNotFoundException extends RuntimeException {
    private final ResourceName resourceName;
    private final List<UUID> resourcesIds;

    public ResourcesNotFoundException(ResourceName resourceName, List<UUID> resourcesIds) {
        super();
        this.resourceName = resourceName;
        this.resourcesIds = resourcesIds;
    }
}
