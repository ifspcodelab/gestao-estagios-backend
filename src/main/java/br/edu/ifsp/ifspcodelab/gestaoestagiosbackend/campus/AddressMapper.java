package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto to(Address address);
}
