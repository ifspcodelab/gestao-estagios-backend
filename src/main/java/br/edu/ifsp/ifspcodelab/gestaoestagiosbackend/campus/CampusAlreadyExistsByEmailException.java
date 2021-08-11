package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import lombok.Getter;

@Getter
public class CampusAlreadyExistsByEmailException extends ResourceAlreadyExistsException {
    public CampusAlreadyExistsByEmailException(String email) {
        super(ResourceName.CAMPUS, "Email", email);
    }
}
