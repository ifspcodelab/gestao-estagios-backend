package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "internships")
@NoArgsConstructor
@Getter
@Setter
public class Internship {
    @Id
    private UUID id;
    @Enumerated(EnumType.STRING)
    private InternshipStatus status;

    @OneToOne
    private AdvisorRequest advisorRequest;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ActivityPlan> activityPlans;

    public Internship(AdvisorRequest advisorRequest) {
        this.id = UUID.randomUUID();
        this.status = InternshipStatus.ACTIVITY_PLAN_PENDING;
        this.advisorRequest = advisorRequest;
    }

    public void addActivityPlan(ActivityPlan activityPlan) {
        this.activityPlans.add(activityPlan);
    }
}
