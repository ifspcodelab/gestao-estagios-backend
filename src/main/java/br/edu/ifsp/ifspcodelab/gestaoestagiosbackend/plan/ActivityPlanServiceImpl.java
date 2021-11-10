package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload.UploadService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Service
public class ActivityPlanServiceImpl implements ActivityPlanService {

    private final ActivityPlanRepository activityPlanRepository;

    private AdvisorRequestService advisorRequestService;
    private UploadService uploadService;

    public ActivityPlanServiceImpl(ActivityPlanRepository activityPlanRepository) {
        this.activityPlanRepository = activityPlanRepository;
    }

    @Autowired
    public void setAdvisorRequestService(AdvisorRequestService advisorRequestService) {
        this.advisorRequestService = advisorRequestService;
    }

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Override
    public ActivityPlan create(UUID advisorRequestId, MultipartFile file) {
        AdvisorRequest advisorRequest = advisorRequestService.findById(advisorRequestId);

        if (!Objects.requireNonNull(file.getContentType()).contains("pdf")) {
            throw new FileExtensionPdfException(file.getContentType());
        }
        String activityPlanUrl = uploadService.uploadFile(
            file,
            advisorRequest.getStudent().getUser().getRegistration() + "/plano-atividades-" + System.currentTimeMillis()
        );
        ActivityPlan activityPlan = new ActivityPlan(
            Instant.now().plus(5, ChronoUnit.DAYS),
            activityPlanUrl,
            advisorRequest
        );

        return activityPlanRepository.save(activityPlan);
    }

    @Override
    public ActivityPlan update(UUID advisorRequestId, UUID activityPlanId, ActivityPlan activityPlan) {
        advisorRequestService.findById(advisorRequestId);
        ActivityPlan plan = activityPlanRepository.findById(activityPlanId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ACTIVITY_PLAN, activityPlanId));

        if(ChronoUnit.DAYS.between(activityPlan.getInternshipStartDate(), activityPlan.getInternshipEndDate()) > 365) {
            throw new DateIntervalYearException(activityPlan.getInternshipStartDate(),
                activityPlan.getInternshipEndDate(),
                365
            );
        }
        plan.setCompanyName(activityPlan.getCompanyName());
        plan.setInternshipStartDate(activityPlan.getInternshipStartDate());
        plan.setInternshipEndDate(activityPlan.getInternshipEndDate());

        return activityPlanRepository.save(plan);
    }
}
