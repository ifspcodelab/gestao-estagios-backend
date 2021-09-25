package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import lombok.Getter;

@Getter
public class UserAlreadyExistsByRegistrationException extends ResourceAlreadyExistsException {
    public UserAlreadyExistsByRegistrationException(String registration) {
        super(ResourceName.USER, "Registration", registration);
    }
}
