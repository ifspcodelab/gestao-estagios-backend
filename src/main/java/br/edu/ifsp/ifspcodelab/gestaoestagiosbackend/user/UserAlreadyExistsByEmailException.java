package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import lombok.Getter;

@Getter
public class UserAlreadyExistsByEmailException extends ResourceAlreadyExistsException {
    public UserAlreadyExistsByEmailException(String email) {
        super(ResourceName.USER, "Email", email);
    }
}
