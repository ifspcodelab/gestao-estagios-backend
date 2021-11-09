package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityPlanRepository extends JpaRepository<ActivityPlan, UUID> {
    List<ActivityPlan> findAllByExpiresAtBeforeAndStatusEquals(Instant expiresAt, RequestStatus status);
}
