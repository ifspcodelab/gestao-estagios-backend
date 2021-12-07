package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "realization_terms")
@NoArgsConstructor
@Getter
@Setter
public class RealizationTerm {
    @Id
    private UUID id;
    private Instant createdAt;
    private LocalDate internshipStartDate;
    private LocalDate internshipEndDate;
    private String realizationTermUrl;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private String details;

    @ManyToOne
    @JsonBackReference
    private Internship internship;

    public RealizationTerm(String realizationTermUrl, Internship internship) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.realizationTermUrl = realizationTermUrl;
        this.status = RequestStatus.PENDING;
        this.internship = internship;
    }
}
