package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipType;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm.RealizationTerm;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReport;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;
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
    private InternshipType internshipType;
    @Enumerated(EnumType.STRING)
    private InternshipStatus status;

    @OneToOne
    private AdvisorRequest advisorRequest;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "internship")
    @JsonManagedReference
    private Set<ActivityPlan> activityPlans;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "internship")
    @JsonManagedReference
    private Set<MonthlyReport> monthlyReports;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "internship")
    @JsonManagedReference
    private Set<RealizationTerm> realizationTerms;

    public Internship(AdvisorRequest advisorRequest) {
        this.id = UUID.randomUUID();
        this.internshipType = advisorRequest.getInternshipType();
        this.status = InternshipStatus.ACTIVITY_PLAN_PENDING;
        this.advisorRequest = advisorRequest;
    }

    public void addActivityPlan(ActivityPlan activityPlan) {
        this.activityPlans.add(activityPlan);
    }

    public void addMonthlyReport(MonthlyReport monthlyReport) {
        this.monthlyReports.add(monthlyReport);
    }

    public Student getStudent() {
        return this.advisorRequest.getStudent();
    }

    public Advisor getAdvisor() {
        return this.advisorRequest.getAdvisor();
    }

    public boolean isInProgress() {
        return this.status == InternshipStatus.IN_PROGRESS;
    }

    public boolean canGenerateFinalDocumentation() {
        return this.getStatus().equals(InternshipStatus.REALIZATION_TERM_ACCEPTED) ||
            this.getStatus().equals(InternshipStatus.FINISHED);
    }
}
