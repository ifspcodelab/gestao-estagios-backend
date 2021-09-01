package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusDtoSimplified;
import lombok.Data;

import java.util.UUID;

@Data
public class DepartmentDtoSimplified {
    UUID id;
    String name;
    String abbreviation;
    CampusDtoSimplified campus;
}
