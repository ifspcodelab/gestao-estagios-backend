package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DateIntervalException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.FileMaxSizeException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload.UploadService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter.ParameterService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private ParameterService parameterService;

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

    @Autowired
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Override
    public ActivityPlan create(UUID advisorRequestId, MultipartFile file) {
        AdvisorRequest advisorRequest = advisorRequestService.findById(advisorRequestId);

        if (!Objects.requireNonNull(file.getContentType()).equals(MediaType.APPLICATION_PDF_VALUE)) {
            throw new FileExtensionPdfException(MediaType.APPLICATION_PDF_VALUE, file.getContentType());
        }
        if (file.getSize() > parameterService.findFirst().getActivityPlanFileSizeMegabytes() * 1048576) {
            throw new FileMaxSizeException(
                parameterService.findFirst().getActivityPlanFileSizeMegabytes() * 1048576,
                file.getSize()
            );
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
    public ActivityPlan update(UUID advisorRequestId, UUID activityPlanId, ActivityPlanUpdateDto activityPlanUpdateDto) {
        advisorRequestService.findById(advisorRequestId);
        ActivityPlan activityPlan = activityPlanRepository.findById(activityPlanId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ACTIVITY_PLAN, activityPlanId));

        if(ChronoUnit.DAYS.between(
            activityPlanUpdateDto.getInternshipStartDate(),
            activityPlanUpdateDto.getInternshipEndDate()) > 365
        ) {
            throw new DateIntervalException(activityPlanUpdateDto.getInternshipStartDate(),
                activityPlanUpdateDto.getInternshipEndDate(),
                365
            );
        }
        activityPlan.setCompanyName(activityPlanUpdateDto.getCompanyName());
        activityPlan.setInternshipStartDate(activityPlanUpdateDto.getInternshipStartDate());
        activityPlan.setInternshipEndDate(activityPlanUpdateDto.getInternshipEndDate());

        return activityPlanRepository.save(activityPlan);
    }
}
