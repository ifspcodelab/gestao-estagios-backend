package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;

public class RealizationTermAlreadyExistsByStatusException extends ResourceAlreadyExistsException {
    public RealizationTermAlreadyExistsByStatusException(RequestStatus status) {
        super(ResourceName.REALIZATION_TERM, "status", status);
    }
}
