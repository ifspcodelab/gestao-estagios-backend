package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.AdvisorService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.MailDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.SenderMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.CreatorParametersMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.FormatterMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount.TemplatesHtml;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter.Parameter;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter.ParameterService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AdvisorRequestServiceImpl implements AdvisorRequestService {

    @Value("${application.mail.username}")
    private String replyTo;

    private AdvisorRequestRepository advisorRequestRepository;

    private StudentService studentService;
    private CurriculumService curriculumService;
    private AdvisorService advisorService;
    private ParameterService parameterService;

    private final SenderMail senderMail;

    public AdvisorRequestServiceImpl(AdvisorRequestRepository advisorRepository, SenderMail senderMail) {
        this.advisorRequestRepository = advisorRepository;
        this.senderMail = senderMail;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @Autowired
    public void setAdvisorService(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @Autowired
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @Override
    public AdvisorRequest create(AdvisorRequestCreateDto advisorRequestCreateDto) {
        Student student = this.studentService.findById(advisorRequestCreateDto.getStudentId());

        List<AdvisorRequest> requests = this.findByStudentId(student.getId());
        requests.forEach(request -> {
            if (request.getStatus() == RequestStatus.PENDING) {
                throw new AdvisorRequestAlreadyExistsByStatus(request.getStatus());
            }
        });

        Curriculum curriculum = this.curriculumService.findByCurriculumId(advisorRequestCreateDto.getCurriculumId());
        Advisor advisor = this.advisorService.findById(advisorRequestCreateDto.getAdvisorId());
        Parameter parameter = this.parameterService.findFirst();

        AdvisorRequest advisorRequest = new AdvisorRequest(
            Instant.now().plus(parameter.getAdvisorRequestDeadline(), ChronoUnit.DAYS),
            advisorRequestCreateDto.getInternshipType(),
            advisorRequestCreateDto.getDetails(),
            student,
            curriculum,
            advisor
        );

        MailDto email = MailDto.builder()
                .title("Novo Pedido de Orientação")
                .msgHTML(TemplatesHtml.getAdvisorRequestNotify())
                .build();

        Date expiresAt = Date.from(advisorRequest.getExpiresAt());
        Locale locale = new Locale("pt","BR");
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy (EEEE), 'às' HH:mm", locale);
        String formattedExpiresAt = sdf.format(expiresAt);

        Map<String, String> params = CreatorParametersMail.setParametersAdvisorRequestNotify(
                advisor.getUser().getName(),
                student.getUser().getName(),
                student.getUser().getRegistration(),
                advisorRequestCreateDto.getInternshipType().getDescription(),
                formattedExpiresAt
        );
        email = FormatterMail.build(email, params);
        email.setRecipientTo(advisor.getUser().getEmail());
        email.setReplyTo(replyTo);
        senderMail.sendEmail(email);

        return this.advisorRequestRepository.save(advisorRequest);
    }

    @Override
    public AdvisorRequest save(AdvisorRequest advisorRequest) {
        return advisorRequestRepository.save(advisorRequest);
    }

    @Override
    public AdvisorRequest findById(UUID id) {
        return advisorRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ADVISOR_REQUEST, id));
    }

    @Override
    public List<AdvisorRequest> findByAdvisorId(UUID id) {
        return this.advisorRequestRepository.findAllByAdvisorId(id);
    }

    @Override
    public List<AdvisorRequest> findByStudentId(UUID id) {
        return this.advisorRequestRepository.findAllByStudentId(id);
    }

    @Override
    public void verifyExpiredRequests() {
        List<AdvisorRequest> expiredRequests = this.advisorRequestRepository
                .findAllByExpiresAtBeforeAndStatusEquals(Instant.now(), RequestStatus.PENDING);

        if(expiredRequests.isEmpty()){
            return;
        }

        for (AdvisorRequest request : expiredRequests) {
            MailDto email = MailDto.builder()
                    .title("Pedido de Orientação Expirou")
                    .msgHTML(TemplatesHtml.getStudentNotificationExpired())
                    .build();

            Date createdAt = Date.from(request.getCreatedAt());
            Locale locale = new Locale("pt","BR");
            SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy (EEEE), 'às' HH:mm", locale);
            String formattedCreatedAt = sdf.format(createdAt);

            Map<String, String> params = CreatorParametersMail
                    .setParametersStudentNotificationExpired(
                            request.getStudent().getUser().getName(),
                            request.getAdvisor().getUser().getName(),
                            formattedCreatedAt,
                            "https://gestao-projetos-frontend.netlify.app/"
                    );

            email = FormatterMail.build(email, params);
            email.setRecipientTo(request.getStudent().getUser().getEmail());
            senderMail.sendEmail(email);

            request.setStatus(RequestStatus.REJECTED);
            this.advisorRequestRepository.save(request);
        }
    }
}
