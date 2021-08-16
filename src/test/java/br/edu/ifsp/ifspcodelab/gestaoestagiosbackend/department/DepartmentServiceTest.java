package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusRepository;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private CampusRepository campusRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    public void setUp() {
        department = DepartmentFactoryUtils.sampleDepartment();
    }

    @Test
    public void createDepartment() {
        getCampus();
        when(departmentRepository.save(any(Department.class))).thenReturn(DepartmentFactoryUtils.sampleDepartment());

        Department departmentCreated = departmentService.create(
            department.getCampus().getId(),
            new DepartmentCreateDto(
                department.getName(),
                department.getAbbreviation()
            )
        );

        verify(departmentRepository, times(1)).save(any(Department.class));

        assertThat(departmentCreated).isNotNull();
        assertThat(departmentCreated.getId()).isNotNull();
        assertThat(departmentCreated.getName()).isEqualTo(department.getName());
        assertThat(departmentCreated.getAbbreviation()).isEqualTo(department.getAbbreviation());
        assertThat(departmentCreated.getCampus().getName()).isEqualTo(department.getCampus().getName());
        assertThat(departmentCreated.getCampus().getInternshipSector().getEmail()).
            isEqualTo(department.getCampus().getInternshipSector().getEmail());
    }

    @Test
    public void createDepartmentAlreadyExistsByAbbreviationAndCampusId() {
        getCampus();
        when(departmentRepository.existsByAbbreviationAndCampusId(
            department.getAbbreviation(),
            department.getCampus().getId())
        ).thenReturn(true);

        assertThatThrownBy(() -> departmentService.create(
            department.getCampus().getId(),
            new DepartmentCreateDto(
                department.getName(),
                department.getAbbreviation()
            )
        )).isInstanceOf(DepartmentAlreadyExistsByAbbreviationAndCampusIdException.class);
    }

    @Test
    public void findAll() {
        getCampus();
        when(departmentRepository.findAllByCampusId(department.getCampus().getId())).thenReturn(List.of(department));

        List<Department> departments = departmentService.findAll(department.getCampus().getId());

        assertThat(departments).hasSize(1);
    }

    @Test
    public void findAllEmpty() {
        getCampus();
        when(departmentRepository.findAllByCampusId(department.getCampus().getId())).thenReturn(Collections.emptyList());

        List<Department> departments = departmentService.findAll(department.getCampus().getId());

        assertThat(departments).isEmpty();
    }

    @Test
    public void findById() {
        getCampus();
        getDepartment();

        Department departmentFound = departmentService.findById(department.getCampus().getId(), department.getId());

        assertThat(departmentFound).isNotNull();
        assertThat(departmentFound.getId()).isEqualTo(department.getId());
    }

    @Test
    public void findByIdNotFound() {
        getCampus();
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.findById(department.getCampus().getId(), department.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void updateDepartment() {
        getCampus();
        getDepartment();
        when(departmentRepository.save(any(Department.class))).thenReturn(DepartmentFactoryUtils.sampleDepartment());

        Department departmentUpdated = departmentService.update(
            department.getCampus().getId(),
            department.getId(),
            new DepartmentCreateDto(
                department.getName(),
                department.getAbbreviation()
            )
        );
        departmentUpdated.setName("Test Department 2");

        verify(departmentRepository, times(1)).save(any(Department.class));

        assertThat(departmentUpdated).isNotNull();
        assertThat(departmentUpdated.getName()).isNotEqualTo(department.getName());
    }

    @Test
    public void updateDepartmentAlreadyExistsByAbbreviationAndCampusIdExcludedId() {
        getCampus();
        getDepartment();
        when(departmentRepository.existsByAbbreviationAndCampusIdExcludedId(
            department.getAbbreviation(),
            department.getCampus().getId(),
            department.getId())
        ).thenReturn(true);

        assertThatThrownBy(() -> departmentService.update(
            department.getCampus().getId(),
            department.getId(),
            new DepartmentCreateDto(
                department.getName(),
                department.getAbbreviation()
            )
        )).isInstanceOf(DepartmentAlreadyExistsByAbbreviationAndCampusIdException.class);
    }

    @Test
    public void deleteCampus() {
        getCampus();
        getDepartment();

        departmentService.delete(department.getCampus().getId(), department.getId());

        verify(departmentRepository, times(1)).deleteById(department.getId());
    }

    @Test
    public void deleteCampusNotFound() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThatThrownBy(() -> departmentService.delete(department.getCampus().getId(), department.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void deleteDepartmentNotFound() {
        getCampus();
        when(departmentRepository.findByCampusIdAndId(department.getCampus().getId(), department.getId()))
            .thenReturn(Optional.empty());
        assertThatThrownBy(() -> departmentService.delete(department.getCampus().getId(), department.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }


    private void getCampus() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(
            Optional.of(DepartmentFactoryUtils.sampleDepartment().getCampus())
        );
    }

    private void getDepartment() {
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
            .thenReturn(Optional.of(department));
    }
}
