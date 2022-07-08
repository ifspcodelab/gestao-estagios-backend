package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "curriculums")
@NoArgsConstructor
@Getter
@Setter
public class Curriculum {
    @Id
    private UUID id;
    private String code;
    private Integer courseLoad;
    private Integer internshipCourseLoad;
    private String internshipStartCriteria;
    private String internshipAllowedActivities;
    private LocalDate validityStartDate;
    private LocalDate validityEndDate;
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @ManyToOne
    private Course course;

    public Curriculum(
        String code,
        Integer courseLoad,
        Integer internshipCourseLoad,
        String internshipStartCriteria,
        String internshipAllowedActivities,
        LocalDate validityStartDate,
        LocalDate validityEndDate,
        Course course
    ) {
        this.id = UUID.randomUUID();
        this.code = code;
        this.courseLoad = courseLoad;
        this.internshipCourseLoad = internshipCourseLoad;
        this.internshipStartCriteria = internshipStartCriteria;
        this.internshipAllowedActivities = internshipAllowedActivities;
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
        this.status = EntityStatus.ENABLED;
        this.course = course;
    }

    public Curriculum enable() {
        this.setStatus(EntityStatus.ENABLED);
        return this;
    }
}
