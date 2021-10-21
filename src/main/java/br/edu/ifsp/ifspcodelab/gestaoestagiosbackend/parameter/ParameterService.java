package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;


public interface ParameterService {
    Parameter findFirst();
    Parameter update(ParameterCreateDto parameterCreateDto);
}
