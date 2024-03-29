package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.appraisal;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.MailDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.SenderMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.CreatorParametersMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.FormatterMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount.TemplatesHtml;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Service
public class RequestAppraisalServiceImpl implements RequestAppraisalService {
    private final RequestAppraisalRepository requestAppraisalRepository;
    private final SenderMail senderMail;

    private AdvisorRequestService advisorRequestService;
    private InternshipService internshipService;

    public RequestAppraisalServiceImpl(RequestAppraisalRepository requestAppraisalRepository, SenderMail senderMail) {
        this.requestAppraisalRepository = requestAppraisalRepository;
        this.senderMail = senderMail;
    }

    @Autowired
    public void setAdvisorRequestService(AdvisorRequestService advisorRequestService) {
        this.advisorRequestService = advisorRequestService;
    }

    @Autowired
    public void setInternshipService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @Override
    public RequestAppraisal create(UUID requestId, RequestAppraisalCreateDto requestAppraisalCreateDto) {
        AdvisorRequest advisorRequest = this.advisorRequestService.findById(requestId);

        RequestAppraisal requestAppraisal = new RequestAppraisal(
            requestAppraisalCreateDto.getDetails(),
            requestAppraisalCreateDto.getIsDeferred(),
            requestAppraisalCreateDto.getMeetingDate(),
            advisorRequest
        );

        MailDto email = MailDto.builder()
            .title("Avaliação de pedido de orientação ["+advisorRequest.getStudent().getUser().getName()+"]")
            .msgHTML(TemplatesHtml.getRequestAppraisal())
            .build();
        String details = requestAppraisalCreateDto.getDetails();
        Map<String, String> params;

        if (requestAppraisalCreateDto.getIsDeferred()) {
            advisorRequest.setStatus(RequestStatus.ACCEPTED);

            if (requestAppraisalCreateDto.getMeetingDate() != null) {
                OffsetDateTime meetingDateFormatted = OffsetDateTime.ofInstant(
                        requestAppraisalCreateDto.getMeetingDate(),
                        ZoneId.of("Etc/Universal")
                );
                DateTimeFormatter customFormatter = DateTimeFormatter
                        .ofPattern("dd 'de' MMMM 'de' yyyy (EEEE), 'às' HH:mm");
                details += "<br/>" + meetingDateFormatted.format(customFormatter);
            }

            params = CreatorParametersMail.setParametersRequestAppraisal(
                "O pedido de orientação foi deferido.",
                advisorRequest.getStudent().getUser().getName(),
                advisorRequest.getAdvisor().getUser().getName(),
                details
            );
            internshipService.create(advisorRequest);
        } else {
            advisorRequest.setStatus(RequestStatus.REJECTED);

            params = CreatorParametersMail.setParametersRequestAppraisal(
                "O pedido de orientação foi indeferido.",
                advisorRequest.getStudent().getUser().getName(),
                advisorRequest.getAdvisor().getUser().getName(),
                details
            );
        }

        email = FormatterMail.build(email, params);
        if (requestAppraisalCreateDto.getSendEmailToAdvisor()) {
            email.setRecipientTo(
                advisorRequest.getAdvisor().getUser().getEmail(),
                advisorRequest.getStudent().getUser().getEmail()
            );
        } else {
            email.setRecipientTo(advisorRequest.getStudent().getUser().getEmail());
        }
        email.setReplyTo(advisorRequest.getAdvisor().getUser().getEmail());
        senderMail.sendEmail(email);

        this.advisorRequestService.save(advisorRequest);
        return requestAppraisalRepository.save(requestAppraisal);
    }
}
