package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipType;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.DateIntervalException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.MailDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.SenderMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.CreatorParametersMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.FormatterMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount.TemplatesHtml;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload.UploadService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ActivityPlanServiceImpl implements ActivityPlanService {

    private final ActivityPlanRepository activityPlanRepository;

    private InternshipService internshipService;
    private UploadService uploadService;
    private MonthlyReportService monthlyReportService;
    private final SenderMail senderMail;

    @Value("${application.frontend.url}")
    private String frontendUrl;

    public ActivityPlanServiceImpl(ActivityPlanRepository activityPlanRepository, SenderMail senderMail) {
        this.activityPlanRepository = activityPlanRepository;
        this.senderMail = senderMail;
    }

    @Autowired
    public void setInternshipService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Autowired
    public void setMonthlyReportService(MonthlyReportService monthlyReportService) {
        this.monthlyReportService = monthlyReportService;
    }

    @Override
    @Transactional
    public ActivityPlan create(UUID internshipId, MultipartFile file) {
        Internship internship = internshipService.findById(internshipId);

        List<Internship> internships =
            internshipService.findAllByAdvisorRequestStudentId(internship.getAdvisorRequest().getStudent().getId());

        internships.forEach(i -> {
            if (i.getStatus() == InternshipStatus.ACTIVITY_PLAN_SENT) {
                throw new ActivityPlanAlreadyExistsByStatusException(i.getStatus());
            }
        });
        uploadService.activityPlanFileValidation(file);

        String activityPlanUrl = uploadService.uploadFile(file, getActivityPlanFileName(internship));
        ActivityPlan activityPlan = new ActivityPlan(
            Instant.now().plus(5, ChronoUnit.DAYS),
            activityPlanUrl,
            internship
        );

        activityPlanRepository.save(activityPlan);

        internship.addActivityPlan(activityPlan);
        internship.setStatus(InternshipStatus.ACTIVITY_PLAN_SENT);
        internshipService.update(internship);

        return activityPlan;
    }

    @Override
    public ActivityPlan update(UUID internshipId, UUID activityPlanId, ActivityPlanUpdateDto activityPlanUpdateDto) {
        internshipService.findById(internshipId);
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

    @Override
    public ActivityPlan appraise(UUID internshipId, UUID activityPlanId, ActivityPlanAppraisalDto activityPlanAppraisalDto) {
        Internship internship = internshipService.findById(internshipId);
        ActivityPlan activityPlan = activityPlanRepository.findById(activityPlanId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ACTIVITY_PLAN, activityPlanId));

        if (activityPlanAppraisalDto.getIsRequired() != null) {
            if (activityPlanAppraisalDto.getIsRequired()) {
                internship.setInternshipType(InternshipType.REQUIRED);
            } else {
                internship.setInternshipType(InternshipType.NOT_REQUIRED);
            }
        }

        if (activityPlanAppraisalDto.getStatus().equals(RequestStatus.ACCEPTED)) {
            internship.setStatus(InternshipStatus.IN_PROGRESS);

            for (
                LocalDate date = activityPlan.getInternshipStartDate().withDayOfMonth(1);
                date.isBefore(activityPlan.getInternshipEndDate());
                date = date.plusMonths(1)
            ) monthlyReportService.create(date, activityPlan);

            MailDto email = MailDto.builder()
                    .title("Deferimento do plano de atividades")
                    .msgHTML(TemplatesHtml.getPlanApproved())
                    .build();

            Map<String, String> params = CreatorParametersMail.setParametersPlanApproved(
                    internship.getAdvisorRequest().getStudent().getUser().getName(),
                    internship.getAdvisorRequest().getAdvisor().getUser().getName(),
                    activityPlanAppraisalDto.getDetails()
            );
            email = FormatterMail.build(email, params);

            email.setRecipientTo(internship.getAdvisorRequest().getStudent().getUser().getEmail());

            senderMail.sendEmail(email);
        } else {
            internship.setStatus(InternshipStatus.ACTIVITY_PLAN_PENDING);

            MailDto email = MailDto.builder()
                    .title("Indeferimento do plano de atividades")
                    .msgHTML(TemplatesHtml.getPlanIndeferred())
                    .build();

            Map<String, String> params = CreatorParametersMail.setParametersPlanIndeferred(
                    internship.getAdvisorRequest().getStudent().getUser().getName(),
                    internship.getAdvisorRequest().getAdvisor().getUser().getName(),
                    activityPlanAppraisalDto.getDetails(),
                    frontendUrl
            );
            email = FormatterMail.build(email, params);

            email.setRecipientTo(internship.getAdvisorRequest().getStudent().getUser().getEmail());

            senderMail.sendEmail(email);
        }

        activityPlan.setDetails(activityPlanAppraisalDto.getDetails());
        activityPlan.setStatus(activityPlanAppraisalDto.getStatus());

        internshipService.update(internship);
        return activityPlanRepository.save(activityPlan);
    }

    private String getActivityPlanFileName(Internship internship) {
        return internship.getAdvisorRequest().getStudent().getUser().getRegistration() +
            "/" +
            internship.getId().toString() +
            "/" +
            System.currentTimeMillis() +
            "-plano-atividades";
    }
}
