package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CampusRepository campusRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    public void setUp() {
        department = DepartmentFactoryUtils.sampleDepartment();
    }

    @Test
    public void saveNewDepartment() {
        campusRepository.save(department.getCampus());
        departmentRepository.save(department);
        Department departmentFound = entityManager.find(Department.class, department.getId());

        assertThat(departmentFound).isNotNull();
        assertThat(departmentFound.getId()).isEqualTo(department.getId());
        assertThat(departmentFound.getName()).isEqualTo(department.getName());
        assertThat(departmentFound.getAbbreviation()).isEqualTo(department.getAbbreviation());
        assertThat(departmentFound.getCampus().getId()).isEqualTo(department.getCampus().getId());
        assertThat(departmentFound.getCampus().getName()).isEqualTo(department.getCampus().getName());
        assertThat(departmentFound.getCampus().getAbbreviation()).isEqualTo(department.getCampus().getAbbreviation());
        assertThat(departmentFound.getCampus().getAddress()).isEqualTo(department.getCampus().getAddress());
        assertThat(departmentFound.getCampus().getInternshipSector()).isEqualTo(department.getCampus().getInternshipSector());
    }

    @Test
    public void countAllDepartmentsByCampusId_ZeroDepartments() {
        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());

        long numberOfDepartments = departmentRepository.countAllByCampusId(campus.getId());

        assertThat(numberOfDepartments).isEqualTo(0L);
    }

    @Test
    public void countAllDepartmentsByCampusId_TwoDepartments() {
        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
        departmentRepository.save(DepartmentFactoryUtils.sampleDepartment("Department A", "DPA", campus));
        departmentRepository.save(DepartmentFactoryUtils.sampleDepartment("Department B", "DPB", campus));

        long numberOfDepartments = departmentRepository.countAllByCampusId(campus.getId());

        assertThat(numberOfDepartments).isEqualTo(2L);
    }
}
