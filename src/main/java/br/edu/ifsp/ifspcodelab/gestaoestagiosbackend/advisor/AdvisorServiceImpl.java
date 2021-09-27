package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public List<Advisor> findAll() {
        return advisorRepository.findAll();
    }

    @Override
    public List<Course> getCourses(List<UUID> coursesIds) {
        return courseService.findByIdIn(coursesIds);
    }
}
