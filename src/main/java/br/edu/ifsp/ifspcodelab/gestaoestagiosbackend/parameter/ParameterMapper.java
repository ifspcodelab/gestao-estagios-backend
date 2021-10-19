package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParameterMapper {
    ParameterDto to (Parameter parameter);
}
