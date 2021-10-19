package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import java.util.List;
import java.util.UUID;

public interface ParameterService {
    List<Parameter> findAll();
    Parameter update(ParameterCreateDto parameterCreateDto);
}
