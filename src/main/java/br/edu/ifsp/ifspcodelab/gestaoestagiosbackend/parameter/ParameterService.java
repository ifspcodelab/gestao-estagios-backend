package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import java.util.List;

public interface ParameterService {
    List<Parameter> findAll();
    Parameter update(ParameterCreateDto parameterCreateDto);
}
