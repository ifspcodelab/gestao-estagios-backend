package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;


import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;

public class CourseFactoryUtils {
    public static Course sampleCourse(Department department) {
        String name = "Test Course";
        String abbreviation = "TC";
        Integer numberOfPeriods = 6;

        return new Course(name, abbreviation, numberOfPeriods, department);
    }
}
