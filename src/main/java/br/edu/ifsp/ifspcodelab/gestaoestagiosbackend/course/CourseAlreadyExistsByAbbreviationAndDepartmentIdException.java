package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsTwoFieldsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CourseAlreadyExistsByAbbreviationAndDepartmentIdException extends ResourceAlreadyExistsTwoFieldsException {
    public CourseAlreadyExistsByAbbreviationAndDepartmentIdException(String abbreviation, UUID departmentId) {
        super(ResourceName.COURSE, "abbreviation", "department id", abbreviation, departmentId);
    }
}
