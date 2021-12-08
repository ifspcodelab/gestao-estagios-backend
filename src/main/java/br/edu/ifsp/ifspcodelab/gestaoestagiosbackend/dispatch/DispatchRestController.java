package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.dispatch;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter.Parameter;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter.ParameterService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlanService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.MonthlyReport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class DispatchRestController {
    private final InternshipService internshipService;
    private final ActivityPlanService activityPlanService;
    private final ParameterService parameterService;

    @GetMapping("api/v1/internships/{internshipId}/activity-plans/{activityPlanId}/initial-dispatch")
    public String show(@PathVariable UUID internshipId, @PathVariable UUID activityPlanId) {
        Internship internship = internshipService.findById(internshipId);
        ActivityPlan activityPlan = activityPlanService.findById(activityPlanId);
        Parameter parameter = parameterService.findFirst();

        return getTemplateEngine().process(parameter.getInitialDispatchHtml(), getInitialContext(internship, activityPlan));
    }

    @GetMapping("api/v1/internships/{internshipId}/final-dispatch")
    public String finalShow(@PathVariable UUID internshipId) {
        Internship internship = internshipService.findById(internshipId);
        Parameter parameter = parameterService.findFirst();

        return getTemplateEngine().process(parameter.getFinalDispatchHtml(), getFinalContext(internship));
    }

    private TemplateEngine getTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();

        StandardMessageResolver messageResolver = new StandardMessageResolver();

        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine.setTemplateResolver(stringTemplateResolver);
        templateEngine.setMessageResolver(messageResolver);
        templateEngine.addDialect(new Java8TimeDialect());
        return templateEngine;
    }

    private Context getInitialContext(Internship internship, ActivityPlan activityPlan) {
        Context context = new Context();
        Map<String, Object> dict = new HashMap<>();
        dict.put("internship", internship);
        dict.put("activityPlan", activityPlan);
        context.setVariables(dict);
        return context;
    }

    private Context getFinalContext(Internship internship) {
        Context context = new Context();
        Map<String, Object> dict = new HashMap<>();
        dict.put("internship", internship);
        dict.put("activityPlans", new ArrayList<>(internship.getActivityPlans().stream()
            .filter(a -> a.getStatus().equals(RequestStatus.ACCEPTED))
            .sorted(Comparator.comparing(ActivityPlan::getCreatedAt)).collect(Collectors.toList())));
        dict.put("monthlyReports", new ArrayList<>(internship.getMonthlyReports().stream()
            .filter(r -> r.getStatus().equals(ReportStatus.FINAL_ACCEPTED))
            .sorted(Comparator.comparing(MonthlyReport::getMonth)).collect(Collectors.toList())));
        dict.put("realizationTerm", internship.getRealizationTerms().stream()
            .filter(term -> term.getStatus().equals(RequestStatus.ACCEPTED))
            .findFirst()
            .orElse(null)
        );
        dict.put("totalNumberOfApprovedHours", internship.getMonthlyReports().stream()
            .filter(r -> r.getStatus().equals(ReportStatus.FINAL_ACCEPTED))
            .map(MonthlyReport::getNumberOfApprovedHours)
            .reduce(0, Integer::sum)
            .toString()
        );
        context.setVariables(dict);
        return context;
    }
}
