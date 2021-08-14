package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import lombok.Data;

import java.util.UUID;

@Data
public class DepartmentDto {
    UUID id;
    String name;
    String abbreviation;
}
