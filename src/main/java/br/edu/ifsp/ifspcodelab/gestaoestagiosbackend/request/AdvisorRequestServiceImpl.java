package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.AdvisorService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipType;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.MailDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.SenderMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.CreatorParametersMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.FormatterMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount.TemplatesHtml;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AdvisorRequestServiceImpl implements AdvisorRequestService{

    private AdvisorRequestRepository advisorRequestRepository;

    private StudentService studentService;
    private CurriculumService curriculumService;
    private AdvisorService advisorService;

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

    @Override
    public AdvisorRequest create(AdvisorRequestCreateDto advisorRequestCreateDto) {

        Student student = this.studentService.findById(advisorRequestCreateDto.getStudentId());

        Curriculum curriculum = this.curriculumService.findByCurriculumId(advisorRequestCreateDto.getCurriculumId());

        Advisor advisor = this.advisorService.findById(advisorRequestCreateDto.getAdvisorId());

        AdvisorRequest advisorRequest = new AdvisorRequest(
            Instant.now().plus(7, ChronoUnit.DAYS),
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

        String internshipType = "";

        if (advisorRequestCreateDto.getInternshipType() == InternshipType.REQUIRED_OR_NOT){
            internshipType = "Estágio obrigatório ou não obrigatório";
        } else if (advisorRequestCreateDto.getInternshipType() == InternshipType.PROFESSIONAL_ENJOYMENT) {
            internshipType = "Aproveitamento profissional";
        } else if (advisorRequestCreateDto.getInternshipType() == InternshipType.PROJECT_EQUIVALENCE) {
            internshipType = "Equiparação de projeto institucional";
        }

        Date expiresAt = Date.from(advisorRequest.getExpiresAt());
        java.util.Locale locale = new java.util.Locale("pt","BR");
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy (EEEE), 'às' HH:mm", locale);
        String formattedExpiresAt = sdf.format(expiresAt);

        Map<String, String> params = CreatorParametersMail.setParametersAdvisorRequestNotify(
                advisor.getUser().getName(),
                student.getUser().getName(),
                student.getUser().getRegistration(),
                internshipType,
                formattedExpiresAt
        );
        email = FormatterMail.build(email, params);

        email.setRecipientTo(advisor.getUser().getEmail());

        senderMail.sendEmail(email);

        return this.advisorRequestRepository.save(advisorRequest);
    }

    @Override
    public List<AdvisorRequest> findByAdvisorId(UUID id) {
        return this.advisorRequestRepository.findAllByAdvisor_Id(id);
    }

    @Override
    public List<AdvisorRequest> findByStudentId(UUID id) {
        return this.advisorRequestRepository.findAllByStudent_Id(id);
    }
}
