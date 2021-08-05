package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CourseExistsForDepartmentException extends RuntimeException {
    private final UUID departmentId;

    public CourseExistsForDepartmentException(UUID departmentId) {
        super();
        this.departmentId = departmentId;
    }
}
