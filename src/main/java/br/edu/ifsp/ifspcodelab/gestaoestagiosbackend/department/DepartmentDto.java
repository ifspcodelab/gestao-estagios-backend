package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import java.util.UUID;

public class DepartmentDto {
    private UUID id;
    private String name;
    private String abbreviation;

    public DepartmentDto(UUID id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
