package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;

public class AdvisorRequestAlreadyExistsByStatus extends ResourceAlreadyExistsException {
    public AdvisorRequestAlreadyExistsByStatus(RequestStatus status) {
        super(ResourceName.ADVISOR_REQUEST, "status", status);
    }
}
