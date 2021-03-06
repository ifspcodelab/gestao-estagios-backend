package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

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
@Table(name = "activity_plans")
@NoArgsConstructor
@Getter
@Setter
public class ActivityPlan {
    @Id
    private UUID id;
    private String companyName;
    private LocalDate internshipStartDate;
    private LocalDate internshipEndDate;
    private Instant createdAt;
    private Instant expiresAt;
    private String activityPlanUrl;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private String details;

    @ManyToOne
    @JsonBackReference
    private Internship internship;

    public ActivityPlan(Instant expiresAt, String activityPlanUrl, Internship internship) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.expiresAt = expiresAt;
        this.activityPlanUrl = activityPlanUrl;
        this.internship = internship;
        this.status = RequestStatus.PENDING;
    }

    public LocalDate startDateFirstDay() {
        return this.internshipStartDate.withDayOfMonth(1);
    }
}
