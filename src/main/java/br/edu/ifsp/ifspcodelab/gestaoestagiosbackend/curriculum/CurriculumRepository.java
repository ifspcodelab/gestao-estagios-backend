package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, UUID> {
    @Query("update Curriculum c set c.status = 'DISABLED' where c.course.id = ?1")
    @Transactional
    @Modifying
    void disableAllByCourseId(UUID courseId);
}
