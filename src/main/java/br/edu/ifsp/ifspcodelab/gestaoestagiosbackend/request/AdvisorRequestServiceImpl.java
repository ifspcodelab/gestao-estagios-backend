package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.AdvisorService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;
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

    public void setAdvisorRequestRepository(AdvisorRequestRepository advisorRequestRepository) {
        this.advisorRequestRepository = advisorRequestRepository;
    }

    private AdvisorRequestMapper advisorRequestMapper;

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
    public List<AdvisorRequestForAdvisorDto> findByAdvisorId(UUID id) {
        List<AdvisorRequest> advisorRequests = this.advisorRequestRepository.findAllByAdvisor_Id(id);
        List<AdvisorRequestForAdvisorDto> advisorRequestForAdvisorDtoList = advisorRequests.stream()
                .map(e -> {
                    return new AdvisorRequestForAdvisorDto(
                            e.getId(),
                            e.getCreatedAt(),
                            e.getExpiresAt(),
                            e.getInternshipType(),
                            e.getDetails(),
                            e.getStatus(),
                            e.getCurriculum().getCode(),
                            e.getStudent().getUser().getName(),
                            e.getStudent().getUser().getRegistration(),
                            e.getStudent().getCurriculum().getCourse().getName(),
                            e.getStudent().getId(),
                            e.getAdvisor().getId(),
                            e.getCurriculum().getId()
                    );
                }).collect(Collectors.toList());
        return advisorRequestForAdvisorDtoList;
    }

    @Override
    public List<AdvisorRequestForStudentDto> findByStudentId(UUID id) {
        List<AdvisorRequest> advisorRequests = this.advisorRequestRepository.findAllByStudent_Id(id);
        /*return advisorRequests.stream()
                .map(e -> this.advisorRequestMapper.to(e))
                .collect(Collectors.toList());*/
        List<AdvisorRequestForStudentDto> advisorRequestForStudentDtoList = advisorRequests.stream()
                .map(e -> {
                    return new AdvisorRequestForStudentDto(
                            e.getId(),
                            e.getCreatedAt(),
                            e.getExpiresAt(),
                            e.getInternshipType(),
                            e.getDetails(),
                            e.getStatus(),
                            e.getCurriculum().getCode(),
                            e.getAdvisor().getUser().getName(),
                            e.getStudent().getId(),
                            e.getAdvisor().getId(),
                            e.getCurriculum().getId()
                    );
                }).collect(Collectors.toList());
        return advisorRequestForStudentDtoList;
    }
}
