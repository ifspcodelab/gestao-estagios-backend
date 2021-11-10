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
        parameters.setInternshipRequiredOrNotMessage(parameterCreateDto.getInternshipRequiredOrNotMessage());
        parameters.setProjectEquivalenceMessage(parameterCreateDto.getProjectEquivalenceMessage());
        parameters.setProfessionalValidationMessage(parameterCreateDto.getProfessionalValidationMessage());
        parameters.setAdvisorRequestDeadline(parameterCreateDto.getAdvisorRequestDeadline());
        parameters.setActivityPlanAppraisalDeadline(parameterCreateDto.getActivityPlanAppraisalDeadline());
        parameters.setActivityPlanLink(parameterCreateDto.getActivityPlanLink());
        parameters.setActivityPlanFileSizeMegabytes(parameterCreateDto.getActivityPlanFileSizeMegabytes());
        parameters.setMonthlyReportFileSizeMegabytes(parameterCreateDto.getMonthlyReportFileSizeMegabytes());
        parameters.setMonthlyReportDraftSubmissionDeadlineMonths(parameterCreateDto.getMonthlyReportDraftSubmissionDeadlineMonths());
        parameters.setMonthlyReportDraftAppraisalDeadlineDays(parameterCreateDto.getMonthlyReportDraftAppraisalDeadlineDays());
        parameters.setMonthlyReportAppraisalDeadlineDays(parameterCreateDto.getMonthlyReportAppraisalDeadlineDays());

        return parameterRepository.save(parameters);
    }
}