package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParameterServiceImpl implements ParameterService {
    private ParameterRepository parameterRepository;

    @Override
    public Parameter findAll() {
        return parameterRepository.findAll().get(0);
    }

    @Override
    public Parameter update(ParameterCreateDto parameterCreateDto) {
        Parameter parameters = this.findAll();
        Parameter parameterUpdated = new Parameter(
                parameterCreateDto.getRequiredOrNot(),
                parameterCreateDto.getProjectEquivalence(),
                parameterCreateDto.getProfessionalEnjoyment(),
                parameterCreateDto.getAdvisorRequestDeadline()
        );
        parameterUpdated.setId(parameters.getId());
        return parameterRepository.save(parameterUpdated);
    }
}
