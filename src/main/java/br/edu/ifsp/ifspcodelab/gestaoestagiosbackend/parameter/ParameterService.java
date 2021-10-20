package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;


public interface ParameterService {
    Parameter findAll();
    Parameter update(ParameterCreateDto parameterCreateDto);
}
