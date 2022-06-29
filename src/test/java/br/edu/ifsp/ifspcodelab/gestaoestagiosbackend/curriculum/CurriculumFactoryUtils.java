package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;

public class CurriculumFactoryUtils {
    public static Curriculum sampleCurriculum(Course course){
        String code = "1001";
        Integer courseLoad = 1001;
        Integer internshipCourseLoad = 360;
        String internshipStartCriteria = "Test internship start criteria";
        String internshipAllowedActivities = "Test internship allowed activities";

        return new Curriculum(code, courseLoad, internshipCourseLoad, internshipStartCriteria, internshipAllowedActivities, course);
    }
}
