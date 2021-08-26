package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, UUID> {
    List<Curriculum> findAllByCourseId(UUID courseId);
    Optional<Curriculum> findAllByCourseIdAndId(UUID courseId, UUID curriculumId);
    @Modifying
    @Query("update Curriculum c set c.status = 'DISABLED' where c.course.id = ?1")
    void disableAllByCourseId(UUID courseId);
    boolean existsByCourseId(UUID courseId);
}
