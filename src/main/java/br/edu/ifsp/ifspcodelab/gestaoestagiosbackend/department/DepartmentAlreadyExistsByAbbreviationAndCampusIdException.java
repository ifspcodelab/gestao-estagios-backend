package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsTwoFieldsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DepartmentAlreadyExistsByAbbreviationAndCampusIdException extends ResourceAlreadyExistsTwoFieldsException {
   public DepartmentAlreadyExistsByAbbreviationAndCampusIdException(String abbreviation, UUID campusId) {
       super(ResourceName.DEPARTMENT, "abbreviation", "campus id", abbreviation, campusId);
   }
}
