package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;

public class ActivityPlanAlreadyExistsByStatusException extends ResourceAlreadyExistsException {
    public ActivityPlanAlreadyExistsByStatusException(InternshipStatus status) {
        super(ResourceName.ACTIVITY_PLAN, "status", status);
    }
}
