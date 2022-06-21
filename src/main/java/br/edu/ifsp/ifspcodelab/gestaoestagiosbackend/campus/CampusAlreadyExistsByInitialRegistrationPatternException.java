package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import lombok.Getter;

@Getter
public class CampusAlreadyExistsByInitialRegistrationPatternException extends ResourceAlreadyExistsException {
    public CampusAlreadyExistsByInitialRegistrationPatternException (String initialRegistrationPattern){
        super(ResourceName.CAMPUS, "Initial Registration Pattern", initialRegistrationPattern );
    }
}
