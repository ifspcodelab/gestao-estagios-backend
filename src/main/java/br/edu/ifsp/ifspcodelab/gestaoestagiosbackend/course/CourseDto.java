package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentDtoSimplified;
import lombok.Value;

import java.util.UUID;

@Value
public class CourseDto {
    UUID id;
    String name;
    String abbreviation;
    Integer numberOfPeriods;
    DepartmentDtoSimplified department;
    EntityStatus status;
}
