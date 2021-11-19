package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.upload.UploadService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.Internship;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class RealizationTermServiceImpl implements RealizationTermService {

    private final RealizationTermRepository realizationTermRepository;
    private InternshipService internshipService;
    private UploadService uploadService;

    public RealizationTermServiceImpl(RealizationTermRepository realizationTermRepository) {
        this.realizationTermRepository = realizationTermRepository;
    }

    @Autowired
    public void setInternshipService(InternshipService internshipService) {
        this.internshipService = internshipService;
    }

    @Autowired
    public void setUploadService(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Override
    @Transactional
    public RealizationTerm create(UUID internshipId, MultipartFile file) {
        Internship internship = internshipService.findById(internshipId);
        uploadService.activityPlanFileValidation(file);

        String realizationTermUrl = uploadService.uploadFile(file, getRealizationTermFileName(internship));

        RealizationTerm realizationTerm = new RealizationTerm(
                internship.getActivityPlans().stream().reduce((first, second) -> second)
                        .orElse(null).getInternshipStartDate(),
                internship.getActivityPlans().stream().reduce((first, second) -> second)
                        .orElse(null).getInternshipEndDate(),
                realizationTermUrl,
                null,
                internship
        );

        return realizationTermRepository.save(realizationTerm);
    }

    @Override
    public RealizationTerm update(UUID internshipId, UUID realizationTermId, RealizationTerm realizationTermUpdate) {
        return null;
    }

    private String getRealizationTermFileName(Internship internship) {
        return internship.getAdvisorRequest().getStudent().getUser().getRegistration() +
                "/" +
                internship.getId().toString() +
                "/" +
                System.currentTimeMillis() +
                "-termo-realizacao";
    }
}
