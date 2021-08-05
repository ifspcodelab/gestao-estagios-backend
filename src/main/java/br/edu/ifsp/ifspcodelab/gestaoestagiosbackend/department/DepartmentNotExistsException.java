package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DepartmentNotExistsException extends RuntimeException {
    private final UUID departmentId;

    public DepartmentNotExistsException(UUID departmentId) {
        super();
        this.departmentId = departmentId;
    }
}
