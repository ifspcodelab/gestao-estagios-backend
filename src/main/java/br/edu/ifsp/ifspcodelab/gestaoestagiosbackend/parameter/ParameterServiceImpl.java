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
                parameterCreateDto.getActivityPlanAppraisalDeadline(),
                parameterCreateDto.getActivityPlanLink(),
                parameterCreateDto.getActivityPlanFileSizeBytes(),
                parameterCreateDto.getMonthlyReportFileSizeBytes(),
                parameterCreateDto.getMonthlyReportDraftSubmissionDeadlineMonths(),
                parameterCreateDto.getMonthlyReportDraftAppraisalDeadlineDays(),
                parameterCreateDto.getMonthlyReportAppraisalDeadlineDays()
        );
        parameterUpdated.setId(parameters.getId());
        return parameterRepository.save(parameterUpdated);
    }
}