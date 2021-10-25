package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdvisorRequestRepository extends JpaRepository<AdvisorRequest, UUID> {
    List<AdvisorRequest> findAllByAdvisorId(UUID advisorId);
    List<AdvisorRequest> findAllByStudentId(UUID studentId);
}
