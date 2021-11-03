package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import java.util.List;
import java.util.UUID;

public interface AdvisorRequestService {
    AdvisorRequest create(AdvisorRequestCreateDto advisorRequestCreateDto);
    AdvisorRequest save(AdvisorRequest advisorRequest);
    AdvisorRequest findById(UUID id);
    List<AdvisorRequest> findByAdvisorId(UUID id);
    List<AdvisorRequest> findByStudentId(UUID id);
    void verifyExpiredRequests();
}
