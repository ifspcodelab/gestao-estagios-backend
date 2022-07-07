package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;


import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;

public class CourseFactoryUtils {
    public static Course sampleCourse(Department department) {
        String name = "Test Course";
        String abbreviation = "TC";
        Integer numberOfPeriods = 6;

        return new Course(name, abbreviation, numberOfPeriods, department);
    }

    public static Course sampleCourseDisabled(Department department) {
        String name = "Test Course";
        String abbreviation = "TC";
        Integer numberOfPeriods = 6;

        return new Course(name, abbreviation, numberOfPeriods, EntityStatus.DISABLED, department);
    }

    public static Course sampleCourseEnabled(Department department) {
        String name = "Test Course";
        String abbreviation = "TC";
        Integer numberOfPeriods = 6;

        return new Course(name, abbreviation, numberOfPeriods, EntityStatus.ENABLED, department);
    }
}