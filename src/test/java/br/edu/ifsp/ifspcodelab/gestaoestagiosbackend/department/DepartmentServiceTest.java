package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceReferentialIntegrityException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.StateFactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private DepartmentRepository departmentRepository;
    @Mock
    private CampusService campusService;
    @Mock
    private CourseService courseService;
    private DepartmentServiceImpl departmentService;
    private Department department;

    @BeforeEach
    public void setUp() {
       departmentService = new DepartmentServiceImpl(departmentRepository);
       departmentService.setCampusService(campusService);
       departmentService.setCourseService(courseService);
       State state = StateFactoryUtils.sampleState();
       City city = CityFactoryUtils.sampleCity(state);
       Campus campus = CampusFactoryUtils.sampleCampus(city);
       department = new Department("Departamento A","DPA", campus);
    }

    @Test
    public void createDepartment() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(department.getCampus());
        when(departmentRepository.existsByAbbreviationAndCampusId(anyString(), any(UUID.class)))
                .thenReturn(false);
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        var departmentCreated = departmentService.create(department.getCampus().getId(), departmentCreateDto);

        verify(departmentRepository, times(1)).save(any(Department.class));
        assertThat(departmentCreated).isNotNull();
        assertThat(departmentCreated.getId()).isNotNull();
        assertThat(departmentCreated.getName()).isEqualTo(departmentCreateDto.getName());
        assertThat(departmentCreated.getAbbreviation()).isEqualTo(departmentCreateDto.getAbbreviation());
    }

    @Test
    public void createDepartmentAlreadyExistsByAbbreviationAndCampusId() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(departmentRepository.existsByAbbreviationAndCampusId(anyString(), any(UUID.class)))
                .thenReturn(true);
        when(campusService.findById(any(UUID.class)))
                .thenReturn(department.getCampus());

        assertThatThrownBy(() -> departmentService.create(department.getCampus().getId(), departmentCreateDto))
                .isInstanceOf(DepartmentAlreadyExistsByAbbreviationAndCampusIdException.class);
    }

    @Test
    public void createDepartmentWithDisabledCampus() {
        department.getCampus().setStatus(EntityStatus.DISABLED);
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(department.getCampus());

        assertThatThrownBy(() -> departmentService.create(department.getCampus().getId(), departmentCreateDto))
                .isInstanceOf(ResourceReferentialIntegrityException.class);
    }

    @Test
    public void findAll() {
        when(departmentRepository.findAllByCampusId(any(UUID.class)))
                .thenReturn(List.of(department));

        List<Department> departments = departmentService.findAll(department.getCampus().getId());

        assertThat(departments).hasSize(1);
    }

    @Test
    public void findAllEmpty() {
        when(departmentRepository.findAllByCampusId(any(UUID.class)))
                .thenReturn(Collections.emptyList());

        List<Department> departments = departmentService.findAll(department.getCampus().getId());

        assertThat(departments).isEmpty();
    }

    @Test
    public void findById() {
        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));

        Department departmentFound = departmentService.findById(department.getId());

        assertThat(departmentFound).isNotNull();
        assertThat(departmentFound.getId()).isEqualTo(department.getId());
    }

    @Test
    public void findByIdNotFound() {
        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.findById(department.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void updateDepartment() {
        Department departmentReturnedByRepository = new Department("Departamento B","DPB", department.getCampus());
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento B", "DPB");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(department.getCampus());
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(departmentRepository.existsByAbbreviationAndCampusIdExcludedId(
                anyString(),
                any(UUID.class),
                any(UUID.class)))
                .thenReturn(false);
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(departmentReturnedByRepository);

        Department departmentUpdated = departmentService.update(
            department.getCampus().getId(),
            department.getId(),
            departmentCreateDto
        );

        verify(departmentRepository, times(1)).save(any(Department.class));
        assertThat(departmentUpdated).isNotNull();
        assertThat(departmentUpdated.getName()).isEqualTo(departmentCreateDto.getName());
    }

    @Test
    public void updateDepartmentNotFound() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento B", "DPB");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(department.getCampus());
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.update(
                department.getCampus().getId(),
                department.getId(),
                departmentCreateDto
        )).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void updateDepartmentAlreadyExistsByAbbreviationAndCampusIdExcludedId() {
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento B", "DPB");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(department.getCampus());
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(departmentRepository.existsByAbbreviationAndCampusIdExcludedId(
                anyString(),
                any(UUID.class),
                any(UUID.class)))
                .thenReturn(true);

        assertThatThrownBy(() -> departmentService.update(
                department.getCampus().getId(),
                department.getId(),
                departmentCreateDto
        )).isInstanceOf(DepartmentAlreadyExistsByAbbreviationAndCampusIdException.class);
    }

    @Test
    public void deleteDepartment() {
        when(campusService.findById(any(UUID.class)))
                .thenReturn(department.getCampus());
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(courseService.existsByDepartmentId(any(UUID.class)))
                .thenReturn(false);

        departmentService.delete(department.getCampus().getId(), department.getId());

        verify(departmentRepository, times(1)).deleteById(department.getId());
    }

    @Test
    public void deleteDepartmentNotFound() {
        when(campusService.findById(any(UUID.class)))
                .thenReturn(department.getCampus());
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.delete(department.getCampus().getId(), department.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void existsByCampusId() {
       when(departmentRepository.existsByCampusId(any(UUID.class)))
               .thenReturn(true);

       boolean result = departmentService.existsByCampusId(department.getCampus().getId());

       assertThat(result).isTrue();
    }

    @Test
    public void departmentNotExistsByCampusId() {
        when(departmentRepository.existsByCampusId(any(UUID.class)))
                .thenReturn(false);

        boolean result = departmentService.existsByCampusId(department.getCampus().getId());

        assertThat(result).isFalse();
    }

    @Test
    public void disableAllByCampusId() {
        when(departmentRepository.findAllByCampusId(any(UUID.class)))
                .thenReturn(List.of(department));

        departmentService.disableAllByCampusId(department.getCampus().getId());

        verify(courseService, times(1)).disableAllByDepartmentId(department.getId());
        verify(departmentRepository, times(1)).disableAllByCampusId(department.getCampus().getId());
    }

    @Test
    public void notDisableAllByCampusIdNotExists() {
        when(departmentRepository.findAllByCampusId(any(UUID.class)))
                .thenReturn(Collections.emptyList());

        departmentService.disableAllByCampusId(department.getCampus().getId());
        verify(departmentRepository, times(1)).findAllByCampusId(department.getCampus().getId());
        verify(courseService, times(0)).disableAllByDepartmentId(department.getId());
        verify(courseService, times(0)).disableAllByDepartmentId(department.getId());
    }

}
