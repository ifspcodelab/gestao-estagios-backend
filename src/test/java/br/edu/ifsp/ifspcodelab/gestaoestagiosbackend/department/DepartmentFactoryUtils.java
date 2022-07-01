package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;

public class DepartmentFactoryUtils {
    public static Department sampleDepartment(Campus campus) {
        return new Department("Test Department", "TDP", campus);
    }

    public static Department sampleDepartment(String name, String abbreviation, Campus campus) {
        return new Department(name, abbreviation, campus);
    }

}
