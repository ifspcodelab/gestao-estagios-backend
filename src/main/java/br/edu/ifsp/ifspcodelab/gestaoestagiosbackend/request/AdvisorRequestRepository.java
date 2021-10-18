package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdvisorRequestRepository extends JpaRepository<AdvisorRequest, UUID> {
    List<AdvisorRequest> findAllByAdvisor_Id(UUID advisorId);
    List<AdvisorRequest> findAllByStudent_Id(UUID studentId);
}
