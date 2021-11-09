package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParameterServiceImpl implements ParameterService {
    private ParameterRepository parameterRepository;

    @Override
    public Parameter findFirst() {
        return parameterRepository.findAll().get(0);
    }

    @Override
    public Parameter update(ParameterCreateDto parameterCreateDto) {
        Parameter parameters = this.findFirst();
        Parameter parameterUpdated = new Parameter(
                parameterCreateDto.getInternshipRequiredOrNotMessage(),
                parameterCreateDto.getProjectEquivalenceMessage(),
                parameterCreateDto.getProfessionalValidationMessage(),
                parameterCreateDto.getAdvisorRequestDeadline(),
                parameterCreateDto.getDeadlineEvaluationActivityPlan(),
                parameterCreateDto.getActivityPlanLink(),
                parameterCreateDto.getDeadlineSubmissionMonthlyDraft(),
                parameterCreateDto.getDeadlineEvaluationMonthlyDraft(),
                parameterCreateDto.getEvaluationPeriodMonthlyReport()
        );
        parameterUpdated.setId(parameters.getId());
        return parameterRepository.save(parameterUpdated);
    }
}