package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;

    @Override
    public Internship create(AdvisorRequest advisorRequest) {
        Internship internship = new Internship(advisorRequest);
        return internshipRepository.save(internship);
    }

    @Override
    public Internship findById(UUID id) {
        return internshipRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.INTERNSHIP, id));
    }

    @Override
    public List<Internship> findAllByAdvisorRequestStudentId(UUID studentId) {
        return internshipRepository.findAllByAdvisorRequestStudentId(studentId);
    }

    @Override
    public List<Internship> findAllByAdvisorRequestAdvisorId(UUID advisorId) {
        return internshipRepository.findAllByAdvisorRequestAdvisorId(advisorId);
    }

    @Override
    public Internship update(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public Internship updateStatus(UUID internshipId) {
        Internship internship = this.internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceName.INTERNSHIP, internshipId));

        internship.setStatus(InternshipStatus.FINISHED);

        return this.internshipRepository.save(internship);
    }
}
