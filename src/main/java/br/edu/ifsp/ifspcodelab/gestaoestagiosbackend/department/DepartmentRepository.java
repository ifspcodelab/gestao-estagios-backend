package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    boolean existsByAbbreviationAndCampusId(String abbreviation, UUID campusId);
    long countAllByCampusId(UUID campusId);
    List<Department> findAllByCampusId(UUID campusId);
    Optional<Department> findByCampusIdAndId(UUID campusId, UUID departmentId);
    @Query("select count(d) > 0 from Department d join Campus as c on d.campus = c.id where d.abbreviation = ?1 and c.id = ?2 and d.id <> ?3")
    boolean existsByAbbreviationAndCampusIdExcludedId(String abbreviation, UUID campusId, UUID departmentId);
}
