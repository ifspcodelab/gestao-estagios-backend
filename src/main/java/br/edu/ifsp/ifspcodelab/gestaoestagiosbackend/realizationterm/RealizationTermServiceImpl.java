package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RealizationTermServiceImpl implements RealizationTermService {

    private final RealizationTermRepository realizationTermRepository;
    private InternshipService internshipService;
    private UploadService uploadService;
    private final SenderMail senderMail;

    @Value("${application.frontend.url}")
    private String frontendUrl;

    public RealizationTermServiceImpl(RealizationTermRepository realizationTermRepository, SenderMail senderMail) {
        this.realizationTermRepository = realizationTermRepository;
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

    @Override
    @Transactional
    public RealizationTerm create(UUID internshipId, MultipartFile file) {
        Internship internship = internshipService.findById(internshipId);
        uploadService.activityPlanFileValidation(file);

        String realizationTermUrl = uploadService.uploadFile(file, getRealizationTermFileName(internship));

        RealizationTerm realizationTerm = new RealizationTerm(
                null,
                null,
                realizationTermUrl,
                "",
                internship
        );

        return realizationTermRepository.save(realizationTerm);
    }

    @Override
    @Transactional
    public RealizationTerm update(UUID internshipId,
                                  UUID realizationTermId,
                                  RealizationTermUpdateDto realizationTermUpdateDto) {
        Internship internship = internshipService.findById(internshipId);

        RealizationTerm realizationTerm = realizationTermRepository.findById(realizationTermId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.REALIZATION_TERM, realizationTermId));

        realizationTerm.setInternshipStartDate(realizationTermUpdateDto.getInternshipStartDate());
        realizationTerm.setInternshipEndDate(realizationTermUpdateDto.getInternshipEndDate());

        RealizationTerm realizationTermSaved = realizationTermRepository.save(realizationTerm);

        MailDto studentEmail = MailDto.builder()
                .title("Termo de Realização de Estágio enviado")
                .msgHTML(TemplatesHtml.getRealizationTermSubmissionStudent())
                .build();

        Map<String, String> paramsStudentEmail = CreatorParametersMail.setRealizationTermSubmissionStudent(
                internship.getAdvisorRequest().getStudent().getUser().getName()
        );
        studentEmail = FormatterMail.build(studentEmail, paramsStudentEmail);
        studentEmail.setRecipientTo(internship.getAdvisorRequest().getStudent().getUser().getEmail());

        MailDto advisorEmail = MailDto.builder()
                .title(
                        "Novo Termo de Realização de Estágio recebido [" +
                                internship.getAdvisorRequest().getStudent().getUser().getName() + "]")
                .msgHTML(TemplatesHtml.getRealizationTermSubmissionAdvisor())
                .build();

        Map<String, String> paramsAdvisorEmail = CreatorParametersMail.setRealizationTermSubmissionAdvisor(
                internship.getAdvisorRequest().getAdvisor().getUser().getName(),
                internship.getAdvisorRequest().getStudent().getUser().getName(),
                frontendUrl
        );
        advisorEmail = FormatterMail.build(advisorEmail, paramsAdvisorEmail);
        advisorEmail.setRecipientTo(internship.getAdvisorRequest().getAdvisor().getUser().getEmail());

        senderMail.sendEmail(studentEmail);
        senderMail.sendEmail(advisorEmail);

        return realizationTermSaved;
    }

    @Override
    public RealizationTerm appraisal(UUID internshipId,
                                     UUID realizationTermId,
                                     RealizationTermAppraisalDto realizationTermAppraisalDto) {

        Internship internship = internshipService.findById(internshipId);

        RealizationTerm realizationTerm = realizationTermRepository.findById(realizationTermId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.REALIZATION_TERM, realizationTermId));

        MailDto email = new MailDto();
        Map params = new HashMap<String, String>();
        if(realizationTermAppraisalDto.getStatus() == RequestStatus.ACCEPTED) {
            email = MailDto.builder()
                    .title("Deferimento do Termo de Realização de Estágio")
                    .msgHTML(TemplatesHtml.getRealizationTermApproved())
                    .build();

            params = CreatorParametersMail.setParametersPlanApproved(
                    internship.getAdvisorRequest().getStudent().getUser().getName(),
                    internship.getAdvisorRequest().getAdvisor().getUser().getName(),
                    realizationTermAppraisalDto.getDetails()
            );
        } else if (realizationTermAppraisalDto.getStatus() == RequestStatus.REJECTED) {
            email = MailDto.builder()
                    .title("Indeferimento do Termo de Realização de Estágio")
                    .msgHTML(TemplatesHtml.getRealizationTermIndeferred())
                    .build();

            params = CreatorParametersMail.setParametersPlanIndeferred(
                    internship.getAdvisorRequest().getStudent().getUser().getName(),
                    internship.getAdvisorRequest().getAdvisor().getUser().getName(),
                    realizationTermAppraisalDto.getDetails(),
                    frontendUrl
            );
        }

        email = FormatterMail.build(email, params);
        email.setRecipientTo(internship.getAdvisorRequest().getStudent().getUser().getEmail());

        realizationTerm.setStatus(realizationTermAppraisalDto.getStatus());
        realizationTerm.setDetails(realizationTermAppraisalDto.getDetails());
        RealizationTerm realizationTermUpdated = realizationTermRepository.save(realizationTerm);

        senderMail.sendEmail(email);

        return realizationTermUpdated;
    }

    private String getRealizationTermFileName(Internship internship) {
        return internship.getAdvisorRequest().getStudent().getUser().getRegistration() +
                "/" +
                internship.getId().toString() +
                "/" +
                System.currentTimeMillis() +
                "-termo-realizacao";
    }
}
