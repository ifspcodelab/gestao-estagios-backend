package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import lombok.Getter;

@Getter
public class CampusAlreadyExistsbyInitialRegistrationPatternException extends ResourceAlreadyExistsException {
    public CampusAlreadyExistsbyInitialRegistrationPatternException (String initialRegistrationPatternException){
        super(ResourceName.INITIAL_REGISTRATION_PATTERN, "Initial Registration Pattern", initialRegistrationPatternException );
    }
}
