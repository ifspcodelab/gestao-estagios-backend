package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.realizationterm;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface RealizationTermService {
    RealizationTerm create(UUID internshipId, MultipartFile file);
    RealizationTerm update(UUID internshipId, UUID realizationTermId, RealizationTermUpdateDto realizationTermUpdateDto);
    RealizationTerm appraisal(UUID internshipId, UUID realizationTermId, RealizationTermAppraisalDto realizationTermAppraisalDto);
}
