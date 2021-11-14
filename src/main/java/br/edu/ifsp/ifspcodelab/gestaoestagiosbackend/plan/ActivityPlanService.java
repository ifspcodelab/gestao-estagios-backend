package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ActivityPlanService {
    ActivityPlan create(UUID internshipId, MultipartFile file);
    ActivityPlan update(UUID internshipId, UUID activityPlanId, ActivityPlanUpdateDto activityPlanUpdateDto);
    ActivityPlan appraise(UUID internshipId, UUID activityPlanId, ActivityPlanAppraisalDto activityPlanAppraisalDto);
}
