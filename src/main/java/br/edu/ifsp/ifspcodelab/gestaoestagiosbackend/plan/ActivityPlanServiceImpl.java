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
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReport;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    private void verifyIfExistsActivityPlanPending(Internship internship) {
        internship.getActivityPlans().forEach(plan -> {
            if (plan.getStatus().equals(RequestStatus.PENDING)) {
                throw new ActivityPlanAlreadyExistsByStatusException(plan.getStatus());
            }
        });
    }

    @Override
    @Transactional
    public ActivityPlan create(UUID internshipId, MultipartFile file) {
        Internship internship = internshipService.findById(internshipId);
        verifyIfExistsActivityPlanPending(internship);
        uploadService.activityPlanFileValidation(file);

        String activityPlanUrl = uploadService.uploadFile(file, getActivityPlanFileName(internship));
        ActivityPlan activityPlan = new ActivityPlan(
            Instant.now().plus(5, ChronoUnit.DAYS),
            activityPlanUrl,
            internship
        );

        activityPlanRepository.save(activityPlan);

        internship.addActivityPlan(activityPlan);
        setInternshipStatusToActivityPlanPending(internship);
        internshipService.update(internship);

        return activityPlan;
    }

    @Override
    public ActivityPlan update(UUID internshipId, UUID activityPlanId, ActivityPlanUpdateDto activityPlanUpdateDto) {
        internshipService.findById(internshipId);
        ActivityPlan activityPlan = getActivityPlan(activityPlanId);

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
    @Transactional
    public ActivityPlan appraise(UUID internshipId, UUID activityPlanId, ActivityPlanAppraisalDto activityPlanAppraisalDto) {
        Internship internship = internshipService.findById(internshipId);
        ActivityPlan activityPlan = getActivityPlan(activityPlanId);
        activityPlan.setDetails(activityPlanAppraisalDto.getDetails());
        activityPlan.setStatus(activityPlanAppraisalDto.getStatus());
        setInternshipType(internship, activityPlanAppraisalDto);

        if (activityPlanAppraisalDto.getStatus().equals(RequestStatus.ACCEPTED)) {
            if (internship.isInProgress()) {
                ActivityPlan previousActivityPlan = activityPlanRepository.findByIdIsNotAndStatusEqualsOrderByCreatedAtDesc(activityPlanId, RequestStatus.ACCEPTED);
                monthlyReportService.deleteAllByActivityPlanIdAndMonthAfter(previousActivityPlan.getId(), activityPlan.startDateFirstDay());
                createMonthlyReports(activityPlan, internship, activityPlan.startDateFirstDay().plusMonths(1));
            } else {
                internship.setStatus(InternshipStatus.IN_PROGRESS);
                createMonthlyReports(activityPlan, internship, activityPlan.startDateFirstDay());
            }
            sendEmailActivityPlanDeferred(internship, activityPlanAppraisalDto);
        } else {
            setInternshipStatusToActivityPlanPending(internship);
            sendEmailActivityPlanIndeferred(internship, activityPlanAppraisalDto);
        }

        internshipService.update(internship);
        return activityPlanRepository.save(activityPlan);
    }

    private void setInternshipStatusToActivityPlanPending(Internship internship) {
        if (!internship.isInProgress()) {
            internship.setStatus(InternshipStatus.ACTIVITY_PLAN_PENDING);
        }
    }

    private ActivityPlan getActivityPlan(UUID activityPlanId) {
        return activityPlanRepository.findById(activityPlanId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ACTIVITY_PLAN, activityPlanId));
    }

    private void setInternshipType(Internship internship, ActivityPlanAppraisalDto activityPlanAppraisalDto) {
        if (activityPlanAppraisalDto.getIsRequired() != null) {
            if (activityPlanAppraisalDto.getIsRequired()) {
                internship.setInternshipType(InternshipType.REQUIRED);
            } else {
                internship.setInternshipType(InternshipType.NOT_REQUIRED);
            }
        }
    }

    private String getActivityPlanFileName(Internship internship) {
        return internship.getAdvisorRequest().getStudent().getUser().getRegistration() +
            "/" +
            internship.getId().toString() +
            "/" +
            System.currentTimeMillis() +
            "-plano-atividades";
    }

    private void createMonthlyReports(ActivityPlan activityPlan, Internship internship, LocalDate fromCurrentMonth) {
        for (;
            fromCurrentMonth.isBefore(activityPlan.getInternshipEndDate());
            fromCurrentMonth = fromCurrentMonth.plusMonths(1)
        ) {
            MonthlyReport report = monthlyReportService.create(fromCurrentMonth, activityPlan, internship);
            internship.addMonthlyReport(report);
        }
    }

    private void sendEmailActivityPlanDeferred(Internship internship, ActivityPlanAppraisalDto activityPlanAppraisalDto) {
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
    }

    private void sendEmailActivityPlanIndeferred(Internship internship, ActivityPlanAppraisalDto activityPlanAppraisalDto) {
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
}
