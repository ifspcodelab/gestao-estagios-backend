package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AdvisorServiceImpl implements AdvisorService {

    private final AdvisorRepository advisorRepository;

    private CourseService courseService;

    private UserService userService;

    public AdvisorServiceImpl(AdvisorRepository advisorRepository) {
        this.advisorRepository = advisorRepository;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
    public List<Advisor> findAllByCourseId(UUID courseId) {
        return advisorRepository.findAllByCoursesId(courseId);
    }

    @Override
    public Advisor findById(UUID id) {
        return advisorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ADVISOR, id));
    }

    @Override
    public Advisor findByUserId(UUID userId) {
        return advisorRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.ADVISOR, userId));
    }

    @Override
    public Set<Course> getCourses(List<UUID> coursesIds) {
        return new HashSet<>(courseService.findByIdIn(coursesIds));
    }

    @Override
    public Advisor setStatus(UUID id, EntityUpdateStatusDto advisorUpdateStatusDto) {
        Advisor advisor = findById(id);
        advisor.getUser().setIsActivated(advisorUpdateStatusDto.getStatus());
        userService.save(advisor.getUser());
        return advisor;
    }
}
