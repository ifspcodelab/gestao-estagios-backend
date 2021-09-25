package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserAdvisorCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisorServiceImpl implements AdvisorService {

    private final AdvisorRepository advisorRepository;

    private CourseService courseService;

    public AdvisorServiceImpl(AdvisorRepository advisorRepository) {
        this.advisorRepository = advisorRepository;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Advisor create(Advisor advisor) {
        return advisorRepository.save(advisor);
    }

    @Override
    public List<Course> getCourses(UserAdvisorCreateDto userAdvisorCreateDto) {
        return courseService.findByIdIn(userAdvisorCreateDto.getCoursesIds());
    }
}
