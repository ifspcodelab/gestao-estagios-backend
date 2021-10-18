package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipType;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "advisor_requests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdvisorRequest {

    @Id
    private UUID id;

    private Instant createdAt;

    private Instant expiresAt;

    @Enumerated(EnumType.STRING)
    private InternshipType internshipType;

    private String details;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @OneToOne
    private Student student;

    @OneToOne
    private Curriculum curriculum;

    @OneToOne
    private Advisor advisor;

    public AdvisorRequest(Instant expiresAt, InternshipType internshipType, String details, Student student, Curriculum curriculum, Advisor advisor) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.expiresAt = expiresAt;
        this.internshipType = internshipType;
        this.details = details;
        this.status = RequestStatus.PENDING;
        this.student = student;
        this.curriculum = curriculum;
        this.advisor = advisor;
    }
}
