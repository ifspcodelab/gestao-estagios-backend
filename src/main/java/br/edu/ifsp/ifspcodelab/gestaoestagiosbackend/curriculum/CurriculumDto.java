package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseDto;
import lombok.Value;

import java.util.UUID;

@Value
public class CurriculumDto {
    UUID id;
    String code;
    Integer courseLoad;
    Integer internshipCourseLoad;
    String internshipStartCriteria;
    String internshipAllowedActivities;
    CourseDto course;
    EntityStatus status;
}
