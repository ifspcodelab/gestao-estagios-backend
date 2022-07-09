package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
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
    private Campus campus;
    private City city;

    @BeforeEach
    public void setUp() {
       departmentService = new DepartmentServiceImpl(departmentRepository);
       departmentService.setCampusService(campusService);
       departmentService.setCourseService(courseService);
       State state = StateFactoryUtils.sampleState();
       city = CityFactoryUtils.sampleCity(state);
       campus = CampusFactoryUtils.sampleCampus(city);
    }

    @Test
    public void createDepartment() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus1);
        when(departmentRepository.existsByAbbreviationAndCampusId(anyString(), any(UUID.class)))
                .thenReturn(false);
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        var departmentCreated = departmentService.create(campus1.getId(), departmentCreateDto);

        verify(departmentRepository, times(1)).save(any(Department.class));
        assertThat(departmentCreated).isNotNull();
        assertThat(departmentCreated.getId()).isNotNull();
        assertThat(departmentCreated.getName()).isEqualTo(departmentCreateDto.getName());
        assertThat(departmentCreated.getAbbreviation()).isEqualTo(departmentCreateDto.getAbbreviation());
    }

    @Test
    public void notCreateDepartmentAlreadyExistsByAbbreviationAndCampusId() {
        Campus campus1 = campus;
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(departmentRepository.existsByAbbreviationAndCampusId(anyString(), any(UUID.class)))
                .thenReturn(true);
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus1);

        assertThatThrownBy(() -> departmentService.create(campus.getId(), departmentCreateDto))
                .isInstanceOf(DepartmentAlreadyExistsByAbbreviationAndCampusIdException.class);
    }

    @Test
    public void notCreateDepartmentWithDisabledCampus() {
        City city1 = city;
        Campus campusDisabled = CampusFactoryUtils.sampleCampusDisabled(city1);
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campusDisabled);

        assertThatThrownBy(() -> departmentService.create(campusDisabled.getId(), departmentCreateDto))
                .isInstanceOf(ResourceReferentialIntegrityException.class);
    }

    @Test
    public void findAll() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(departmentRepository.findAllByCampusId(any(UUID.class)))
                .thenReturn(List.of(department));

        List<Department> departments = departmentService.findAll(campus.getId());

        assertThat(departments).hasSize(1);
    }

    @Test
    public void findAllEmpty() {
        Campus campus1 = campus;
        when(departmentRepository.findAllByCampusId(any(UUID.class)))
                .thenReturn(Collections.emptyList());

        List<Department> departments = departmentService.findAll(campus1.getId());

        assertThat(departments).isEmpty();
    }

    @Test
    public void findById() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(department));

        Department departmentFound = departmentService.findById(department.getId());

        assertThat(departmentFound).isNotNull();
        assertThat(departmentFound.getId()).isEqualTo(department.getId());
    }

    @Test
    public void findByIdNotFound() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(departmentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.findById(department.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void findByCampusIdAndId() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));

        Department departmentFound = departmentService.findByCampusIdAndId(campus1.getId(), department.getId());

        assertThat(departmentFound).isNotNull();
        assertThat(departmentFound.getId()).isEqualTo(department.getId());
    }

    @Test
    public void notFindByCampusIdAndId() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.findByCampusIdAndId(campus1.getId(), department.getId()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void updateDepartment() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        Department departmentReturnedByRepository = new Department("Departamento B","DPB", department.getCampus());
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento B", "DPB");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus1);
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
            campus1.getId(),
            department.getId(),
            departmentCreateDto
        );

        verify(departmentRepository, times(1)).save(any(Department.class));
        assertThat(departmentUpdated).isNotNull();
        assertThat(departmentUpdated.getName()).isEqualTo(departmentCreateDto.getName());
    }

    @Test
    public void notUpdateDepartmentNotFound() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento B", "DPB");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus1);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.update(
                campus1.getId(),
                department.getId(),
                departmentCreateDto
        )).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void notUpdateDepartmentAlreadyExistsByAbbreviationAndCampusIdExcludedId() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento B", "DPB");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus1);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(departmentRepository.existsByAbbreviationAndCampusIdExcludedId(
                anyString(),
                any(UUID.class),
                any(UUID.class)))
                .thenReturn(true);

        assertThatThrownBy(() -> departmentService.update(
                campus1.getId(),
                department.getId(),
                departmentCreateDto
        )).isInstanceOf(DepartmentAlreadyExistsByAbbreviationAndCampusIdException.class);
    }

    @Test
    public void setStatus() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        EntityUpdateStatusDto entityUpdateStatusDto = new EntityUpdateStatusDto();
        entityUpdateStatusDto.setStatus(EntityStatus.ENABLED);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        Department departmentWithUpdatedStatus = departmentService.setStatus(campus1.getId(), department.getId(), entityUpdateStatusDto);

        assertThat(departmentWithUpdatedStatus.getStatus()).isEqualTo(entityUpdateStatusDto.getStatus());
    }

    @Test
    public void setStatusWhenDepartmentIsDisabled() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        EntityUpdateStatusDto entityUpdateStatusDto = new EntityUpdateStatusDto();
        entityUpdateStatusDto.setStatus(EntityStatus.DISABLED);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        Department departmentWithUpdatedStatus = departmentService.setStatus(campus1.getId(), department.getId(), entityUpdateStatusDto);

        verify(courseService,times(1)).disableAllByDepartmentId(department.getId());
        assertThat(departmentWithUpdatedStatus.getStatus()).isEqualTo(entityUpdateStatusDto.getStatus());
    }

    @Test
    public void notSetStatusWhenDepartmentIsNotFound() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        EntityUpdateStatusDto entityUpdateStatusDto = new EntityUpdateStatusDto();
        entityUpdateStatusDto.setStatus(EntityStatus.ENABLED);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.setStatus(campus1.getId(), department.getId(), entityUpdateStatusDto))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void deleteDepartment() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus1);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(courseService.existsByDepartmentId(any(UUID.class)))
                .thenReturn(false);

        departmentService.delete(campus1.getId(), department.getId());

        verify(departmentRepository, times(1)).deleteById(department.getId());
    }

    @Test
    public void notDeleteDepartmentNotFound() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus1);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.delete(campus1.getId(), department.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void existsByCampusId() {
        Campus campus1 = campus;
        when(departmentRepository.existsByCampusId(any(UUID.class)))
               .thenReturn(true);

        boolean result = departmentService.existsByCampusId(campus1.getId());

        assertThat(result).isTrue();
    }

    @Test
    public void departmentNotExistsByCampusId() {
        Campus campus1 = campus;
        when(departmentRepository.existsByCampusId(any(UUID.class)))
                .thenReturn(false);

        boolean result = departmentService.existsByCampusId(campus1.getId());

        assertThat(result).isFalse();
    }

    @Test
    public void disableAllByCampusId() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(departmentRepository.findAllByCampusId(any(UUID.class)))
                .thenReturn(List.of(department));

        departmentService.disableAllByCampusId(campus1.getId());

        verify(courseService, times(1)).disableAllByDepartmentId(department.getId());
        verify(departmentRepository, times(1)).disableAllByCampusId(campus1.getId());
    }

    @Test
    public void notDisableAllByCampusIdNotExists() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        when(departmentRepository.findAllByCampusId(any(UUID.class)))
                .thenReturn(Collections.emptyList());

        departmentService.disableAllByCampusId(campus1.getId());

        verify(departmentRepository, times(1)).findAllByCampusId(campus1.getId());
        verify(courseService, times(0)).disableAllByDepartmentId(department.getId());
        verify(courseService, times(0)).disableAllByDepartmentId(department.getId());
    }

    @Test
    public void enable() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        department.setStatus(EntityStatus.DISABLED);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        Department departmentEnabled = departmentService.enable(campus1.getId(), department.getId());

        assertThat(departmentEnabled.getStatus()).isEqualTo(EntityStatus.ENABLED);
    }

    @Test
    public void enableWhenCampusIsDisabled() {
        Campus campusDisabled = CampusFactoryUtils.sampleCampusDisabled(city);
        Department department = new Department("Departamento A","DPA", campusDisabled);
        department.setStatus(EntityStatus.DISABLED);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.of(department));
        when(campusService.enable(any(UUID.class)))
                .thenReturn(campusDisabled);
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        Department departmentEnabled = departmentService.enable(campusDisabled.getId(), department.getId());

        assertThat(departmentEnabled.getStatus()).isEqualTo(EntityStatus.ENABLED);
    }

    @Test
    public void notEnableWhenDepartmentIsNotFound() {
        Campus campus1 = campus;
        Department department = new Department("Departamento A","DPA", campus1);
        department.setStatus(EntityStatus.DISABLED);
        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class)))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.enable(campus1.getId(), department.getId()))
                .isInstanceOf(ResourceNotFoundException.class);
    }


}
