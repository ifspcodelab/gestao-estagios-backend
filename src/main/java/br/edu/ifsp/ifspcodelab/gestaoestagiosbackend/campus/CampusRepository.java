package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampusRepository extends JpaRepository<Campus, UUID> {
    boolean existsByAbbreviation(String abbreviation);
    boolean existsByInternshipSectorEmail(String email);
    @Query("select count(c) > 0 from Campus c where c.internshipSector.email = ?1 and c.id <> ?2")
    boolean existsByEmailExcludedId(String email, UUID id);
    @Query("select count(c) > 0 from Campus c where c.abbreviation = ?1 and c.id <> ?2")
    boolean existsByAbbreviationExcludedId(String abbreviation, UUID id);
    @Query("select u from Campus u where u.status = :status")
    List<Campus> findAllByStatus (@Param("status") EntityStatus status);

}
