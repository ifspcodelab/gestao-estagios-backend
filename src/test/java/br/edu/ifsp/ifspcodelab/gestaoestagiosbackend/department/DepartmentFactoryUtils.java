package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;

public class DepartmentFactoryUtils {
    public static Department sampleDepartment(Campus campus)
    {
        String name = "Test Department";
        String abbreviation = "TDP";
        return new Department(name, abbreviation, campus);
    }

}
