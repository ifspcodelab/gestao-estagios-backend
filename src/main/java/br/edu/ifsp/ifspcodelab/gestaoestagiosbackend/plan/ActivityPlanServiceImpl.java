package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class ActivityPlanServiceImpl implements ActivityPlanService {

    private ActivityPlanRepository activityPlanRepository;

    private AdvisorRequestService advisorRequestService;

    public ActivityPlanServiceImpl(ActivityPlanRepository activityPlanRepository) {
        this.activityPlanRepository = activityPlanRepository;
    }

    @Autowired
    public void setAdvisorRequestService(AdvisorRequestService advisorRequestService) {
        this.advisorRequestService = advisorRequestService;
    }

    @Override
    public ActivityPlan create(UUID advisorRequestId, ActivityPlan activityPlan) {
        AdvisorRequest advisorRequest = advisorRequestService.findById(advisorRequestId);

        ActivityPlan activityPlanCreated = new ActivityPlan(
            activityPlan.getCreatedAt().plus(5, ChronoUnit.DAYS),
            activityPlan.getActivityPlanUrl(),
            advisorRequest
        );

        return activityPlanRepository.save(activityPlanCreated);
    }

    @Override
    public ActivityPlan update(ActivityPlan activityPlan) {
        ActivityPlan plan = activityPlanRepository.findById(activityPlan.getId())
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ACTIVITY_PLAN, activityPlan.getId()));

        return activityPlanRepository.save(plan);
    }
}
