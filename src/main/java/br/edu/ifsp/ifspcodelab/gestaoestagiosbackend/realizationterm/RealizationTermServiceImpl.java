package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
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

    private void verifyIfExistsRealizationTermPending(Internship internship) {
        internship.getRealizationTerms().forEach(e -> {
            if (e.getStatus() == RequestStatus.PENDING) {
                throw new RealizationTermAlreadyExistsByStatusException(e.getStatus());
            }
        });
    }

    @Override
    @Transactional
    public RealizationTerm create(UUID internshipId, MultipartFile file) {
        Internship internship = internshipService.findById(internshipId);
        verifyIfExistsRealizationTermPending(internship);
        uploadService.activityPlanFileValidation(file);

        String realizationTermUrl = uploadService.uploadFile(file, getRealizationTermFileName(internship));
        RealizationTerm realizationTerm = new RealizationTerm(realizationTermUrl, internship);

        return realizationTermRepository.save(realizationTerm);
    }

    @Override
    @Transactional
    public RealizationTerm update(UUID internshipId, UUID realizationTermId, RealizationTermUpdateDto realizationTermUpdateDto) {
        Internship internship = internshipService.findById(internshipId);
        RealizationTerm realizationTerm = getRealizationTerm(realizationTermId);
        realizationTerm.setInternshipStartDate(realizationTermUpdateDto.getInternshipStartDate());
        realizationTerm.setInternshipEndDate(realizationTermUpdateDto.getInternshipEndDate());

        sendEmailRealizationTermSentToStudent(internship);
        sendEmailRealizationTermSentToAdvisor(internship);

        return realizationTermRepository.save(realizationTerm);
    }

    @Override
    public RealizationTerm appraisal(UUID internshipId, UUID realizationTermId, RealizationTermAppraisalDto realizationTermAppraisalDto) {
        Internship internship = internshipService.findById(internshipId);
        RealizationTerm realizationTerm = getRealizationTerm(realizationTermId);
        realizationTerm.setStatus(realizationTermAppraisalDto.getStatus());
        realizationTerm.setDetails(realizationTermAppraisalDto.getDetails());

        if(realizationTermAppraisalDto.getStatus() == RequestStatus.ACCEPTED) {
            internship.setStatus(InternshipStatus.REALIZATION_TERM_ACCEPTED);
            sendEmailRealizationTermDeferred(internship, realizationTermAppraisalDto);
        } else {
            sendEmailRealizationTermIndeferred(internship, realizationTermAppraisalDto);
        }

        internshipService.update(internship);
        return realizationTermRepository.save(realizationTerm);
    }

    private String getRealizationTermFileName(Internship internship) {
        return internship.getAdvisorRequest().getStudent().getUser().getRegistration() +
            "/" +
            internship.getId().toString() +
            "/" +
            System.currentTimeMillis() +
            "-termo-realizacao";
    }

    private RealizationTerm getRealizationTerm(UUID realizationTermId) {
        return realizationTermRepository.findById(realizationTermId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.REALIZATION_TERM, realizationTermId));
    }

    private void sendEmailRealizationTermSentToStudent(Internship internship) {
        MailDto email = MailDto.builder()
            .title("Termo de Realização de Estágio enviado")
            .msgHTML(TemplatesHtml.getRealizationTermSubmissionStudent())
            .build();

        Map<String, String> params = CreatorParametersMail.setRealizationTermSubmissionStudent(
            internship.getAdvisorRequest().getStudent().getUser().getName()
        );
        email = FormatterMail.build(email, params);
        email.setRecipientTo(internship.getAdvisorRequest().getStudent().getUser().getEmail());

        senderMail.sendEmail(email);
    }

    private void sendEmailRealizationTermSentToAdvisor(Internship internship) {
        MailDto email = MailDto.builder()
            .title(
                "Novo Termo de Realização de Estágio recebido [" +
                    internship.getAdvisorRequest().getStudent().getUser().getName() + "]")
            .msgHTML(TemplatesHtml.getRealizationTermSubmissionAdvisor())
            .build();

        Map<String, String> params = CreatorParametersMail.setRealizationTermSubmissionAdvisor(
            internship.getAdvisorRequest().getAdvisor().getUser().getName(),
            internship.getAdvisorRequest().getStudent().getUser().getName(),
            frontendUrl
        );
        email = FormatterMail.build(email, params);
        email.setRecipientTo(internship.getAdvisorRequest().getAdvisor().getUser().getEmail());

        senderMail.sendEmail(email);
    }

    private void sendEmailRealizationTermDeferred(Internship internship, RealizationTermAppraisalDto realizationTermAppraisalDto) {
        MailDto email = MailDto.builder()
            .title("Deferimento do Termo de Realização de Estágio")
            .msgHTML(TemplatesHtml.getRealizationTermApproved())
            .build();

        Map<String, String> params = CreatorParametersMail.setParametersPlanApproved(
            internship.getAdvisorRequest().getStudent().getUser().getName(),
            internship.getAdvisorRequest().getAdvisor().getUser().getName(),
            realizationTermAppraisalDto.getDetails()
        );
        email = FormatterMail.build(email, params);
        email.setRecipientTo(internship.getAdvisorRequest().getStudent().getUser().getEmail());
        senderMail.sendEmail(email);
    }

    private void sendEmailRealizationTermIndeferred(Internship internship, RealizationTermAppraisalDto realizationTermAppraisalDto) {
        MailDto email = MailDto.builder()
            .title("Indeferimento do Termo de Realização de Estágio")
            .msgHTML(TemplatesHtml.getRealizationTermIndeferred())
            .build();
        Map<String, String> params = CreatorParametersMail.setParametersPlanIndeferred(
            internship.getAdvisorRequest().getStudent().getUser().getName(),
            internship.getAdvisorRequest().getAdvisor().getUser().getName(),
            realizationTermAppraisalDto.getDetails(),
            frontendUrl
        );
        email = FormatterMail.build(email, params);
        email.setRecipientTo(internship.getAdvisorRequest().getStudent().getUser().getEmail());
        senderMail.sendEmail(email);
    }
}
