package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.AdvisorDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.AdvisorMapper;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.AdvisorService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseMapper;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumMapper;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.StudentDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.StudentMapper;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdvisorRequestServiceImpl implements AdvisorRequestService{

    private AdvisorRequestRepository advisorRequestRepository;

    private StudentService studentService;
    private CurriculumService curriculumService;
    private AdvisorService advisorService;

    public AdvisorRequestServiceImpl(AdvisorRequestRepository advisorRepository) {
        this.advisorRequestRepository = advisorRepository;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @Autowired
    public void setAdvisorService(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @Override
    public AdvisorRequest create(AdvisorRequestCreateDto advisorRequestCreateDto) {

        Student student = this.studentService.findById(advisorRequestCreateDto.getStudentId());

        Curriculum curriculum = this.curriculumService.findByCurriculumId(advisorRequestCreateDto.getCurriculumId());

        Advisor advisor = this.advisorService.findById(advisorRequestCreateDto.getAdvisorId());

        AdvisorRequest advisorRequest = new AdvisorRequest(
            Instant.now().plus(7, ChronoUnit.DAYS),
            advisorRequestCreateDto.getInternshipType(),
            advisorRequestCreateDto.getDetails(),
            student,
            curriculum,
            advisor
        );

        return this.advisorRequestRepository.save(advisorRequest);
    }

    @Override
    public List<AdvisorRequest> findByAdvisorId(UUID id) {
        return this.advisorRequestRepository.findAllByAdvisor_Id(id);
    }

    @Override
    public List<AdvisorRequest> findByStudentId(UUID id) {
        return this.advisorRequestRepository.findAllByStudent_Id(id);
    }
}
