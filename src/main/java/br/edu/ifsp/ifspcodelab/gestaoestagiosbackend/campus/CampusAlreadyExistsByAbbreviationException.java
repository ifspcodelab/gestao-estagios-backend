package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import lombok.Getter;

@Getter
public class CampusAlreadyExistsByAbbreviationException extends ResourceAlreadyExistsException {
    public CampusAlreadyExistsByAbbreviationException(String abbreviation) {
        super(ResourceName.CAMPUS, "Abbreviation", abbreviation);
    }
}
