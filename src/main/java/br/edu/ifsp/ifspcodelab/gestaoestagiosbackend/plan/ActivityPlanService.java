package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ActivityPlanService {
    ActivityPlan create(UUID advisorRequestId, MultipartFile file);
    ActivityPlan update(ActivityPlan activityPlan);
}
