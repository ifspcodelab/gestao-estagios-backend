package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.appraisal;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "advisor_request_appraisals")
@NoArgsConstructor
@Getter
@Setter
public class RequestAppraisal {

    @Id
    private UUID id;

    private Instant createdAt;

    private String details;

    private Boolean isDeferred;

    private Instant meetingDate;

    @OneToOne
    private AdvisorRequest advisorRequest;

    public RequestAppraisal(String details, Boolean isDeferred, Instant meetingDate, AdvisorRequest advisorRequest) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.details = details;
        this.isDeferred = isDeferred;
        this.meetingDate = meetingDate;
        this.advisorRequest = advisorRequest;
    }
}
