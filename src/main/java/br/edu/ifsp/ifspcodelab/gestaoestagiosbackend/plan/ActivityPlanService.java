package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import java.util.UUID;

public interface ActivityPlanService {
    ActivityPlan create(UUID advisorRequestId, ActivityPlan activityPlan);
    ActivityPlan update(ActivityPlan activityPlan);
}
