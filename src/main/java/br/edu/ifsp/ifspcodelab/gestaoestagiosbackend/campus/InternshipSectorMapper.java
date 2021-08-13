package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipSectorMapper {
    InternshipSectorDto to(InternshipSector internshipSector);
}
