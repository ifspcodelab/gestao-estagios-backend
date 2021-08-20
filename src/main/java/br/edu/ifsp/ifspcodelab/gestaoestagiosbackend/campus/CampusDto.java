package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CampusDto {
    UUID id;
    String name;
    String abbreviation;
    AddressDto address;
    InternshipSectorDto internshipSector;
    List<DepartmentDto> departments;
}
