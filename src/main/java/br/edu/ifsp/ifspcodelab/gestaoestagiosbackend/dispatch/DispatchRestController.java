package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.dispatch;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter.Parameter;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter.ParameterService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlanService;
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

        TemplateEngine templateEngine = new TemplateEngine();

        Context context = new Context();
        Map<String, Object> dict = new HashMap<>();
        dict.put("internship", internship);
        dict.put("activityPlan", activityPlan);
        context.setVariables(dict);

        StandardMessageResolver messageResolver = new StandardMessageResolver();

        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine.setTemplateResolver(stringTemplateResolver);
        templateEngine.setMessageResolver(messageResolver);
        templateEngine.addDialect(new Java8TimeDialect());

        return templateEngine.process(parameter.getInitialDispatchHtml(), context);
    }

    @GetMapping("api/v1/internships/{internshipId}/activity-plans/{activityPlanId}/final-dispatch")
    public String finalShow(@PathVariable UUID internshipId, @PathVariable UUID activityPlanId) {
        Internship internship = internshipService.findById(internshipId);
        ActivityPlan activityPlan = activityPlanService.findById(activityPlanId);
        Parameter parameter = parameterService.findFirst();

//        Set<City> cities = Set.of(city, city2);
//        List<City> citiesList = new ArrayList<>(cities);
//        citiesList.sort(Comparator.comparing(City::getName));

//        internship.getRealizationTerms().stream().filter(realizationTerm -> {
//
//
//            return
//        });

        TemplateEngine templateEngine = new TemplateEngine();

        Context context = new Context();
        Map<String, Object> dict = new HashMap<>();
        dict.put("internship", internship);
        dict.put("activityPlan", activityPlan);
        context.setVariables(dict);

        StandardMessageResolver messageResolver = new StandardMessageResolver();

        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine.setTemplateResolver(stringTemplateResolver);
        templateEngine.setMessageResolver(messageResolver);
        templateEngine.addDialect(new Java8TimeDialect());

        return templateEngine.process(parameter.getFinalDispatchHtml(), context);
    }
}
