package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParameterServiceImpl implements ParameterService {
    private ParameterRepository parameterRepository;

    @Override
    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }

    @Override
    public Parameter update(ParameterCreateDto parameterCreateDto) {
        List<Parameter> parameters = this.findAll();
        Parameter parameterUpdated = new Parameter(
                parameterCreateDto.getRequiredOrNot(),
                parameterCreateDto.getProjectEquivalence(),
                parameterCreateDto.getProfessionalEnjoyment(),
                parameterCreateDto.getAdvisorRequestDeadline()
        );
        parameterUpdated.setId(parameters.get(0).getId());
        return parameterRepository.save(parameterUpdated);
    }
}
