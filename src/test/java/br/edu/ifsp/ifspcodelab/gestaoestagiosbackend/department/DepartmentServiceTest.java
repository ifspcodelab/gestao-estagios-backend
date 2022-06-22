package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceReferentialIntegrityException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.StateFactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

    @BeforeEach
    public void setUp() {
       departmentService = new DepartmentServiceImpl(departmentRepository);
       departmentService.setCampusService(campusService);
       departmentService.setCourseService(courseService);
    }

    @Test
    public void createDepartment() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);
        Department department = new Department("Departamento A","DPA", campus);
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus);
        when(departmentRepository.existsByAbbreviationAndCampusId(anyString(), any(UUID.class)))
                .thenReturn(false);
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        var departmentCreated = departmentService.create(campus.getId(), departmentCreateDto);

        verify(departmentRepository, times(1)).save(any(Department.class));
        assertThat(departmentCreated).isNotNull();
        assertThat(departmentCreated.getId()).isNotNull();
        assertThat(departmentCreated.getName()).isEqualTo(departmentCreateDto.getName());
        assertThat(departmentCreated.getAbbreviation()).isEqualTo(departmentCreateDto.getAbbreviation());
    }

    @Test
    public void createDepartmentAlreadyExistsByAbbreviationAndCampusId() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(departmentRepository.existsByAbbreviationAndCampusId(
                departmentCreateDto.getAbbreviation(),
                campus.getId())
        ).thenReturn(true);
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus);

        assertThatThrownBy(() -> departmentService.create(campus.getId(), departmentCreateDto))
                .isInstanceOf(DepartmentAlreadyExistsByAbbreviationAndCampusIdException.class);
    }

    @Test
    public void createDepartmentWithDisabledCampus() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);
        campus.setStatus(EntityStatus.DISABLED);
        Department department = new Department("Departamento A","DPA", campus);
        DepartmentCreateDto departmentCreateDto = new DepartmentCreateDto("Departamento A", "DPA");
        when(campusService.findById(any(UUID.class)))
                .thenReturn(campus);

        assertThatThrownBy(() -> departmentService.create(campus.getId(), departmentCreateDto))
                .isInstanceOf(ResourceReferentialIntegrityException.class);
    }
//
//    @Test
//    public void findAll() {
//        getCampus();
//        when(departmentRepository.findAllByCampusId(department.getCampus().getId())).thenReturn(List.of(department));
//
//        List<Department> departments = departmentService.findAll(department.getCampus().getId());
//
//        assertThat(departments).hasSize(1);
//    }
//
//    @Test
//    public void findAllEmpty() {
//        getCampus();
//        when(departmentRepository.findAllByCampusId(department.getCampus().getId())).thenReturn(Collections.emptyList());
//
//        List<Department> departments = departmentService.findAll(department.getCampus().getId());
//
//        assertThat(departments).isEmpty();
//    }
//
//    @Test
//    public void findById() {
//        getCampus();
//        getDepartment();
//
//        Department departmentFound = departmentService.findById(department.getCampus().getId(), department.getId());
//
//        assertThat(departmentFound).isNotNull();
//        assertThat(departmentFound.getId()).isEqualTo(department.getId());
//    }
//
//    @Test
//    public void findByIdNotFound() {
//        getCampus();
//        when(departmentRepository.findByCampusIdAndId(any(UUID.class), any(UUID.class))).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> departmentService.findById(department.getCampus().getId(), department.getId()))
//            .isInstanceOf(ResourceNotFoundException.class);
//    }
//
//    @Test
//    public void updateDepartment() {
//        getCampus();
//        getDepartment();
//        when(departmentRepository.save(any(Department.class))).thenReturn(DepartmentFactoryUtils.sampleDepartment());
//
//        Department departmentUpdated = departmentService.update(
//            department.getCampus().getId(),
//            department.getId(),
//            new DepartmentCreateDto(
//                department.getName(),
//                department.getAbbreviation()
//            )
//        );
//        departmentUpdated.setName("Test Department 2");
//
//        verify(departmentRepository, times(1)).save(any(Department.class));
//
//        assertThat(departmentUpdated).isNotNull();
//        assertThat(departmentUpdated.getName()).isNotEqualTo(department.getName());
//    }
//
//    @Test
//    public void updateDepartmentAlreadyExistsByAbbreviationAndCampusIdExcludedId() {
//        getCampus();
//        getDepartment();
//        when(departmentRepository.existsByAbbreviationAndCampusIdExcludedId(
//            department.getAbbreviation(),
//            department.getCampus().getId(),
//            department.getId())
//        ).thenReturn(true);
//
//        assertThatThrownBy(() -> departmentService.update(
//            department.getCampus().getId(),
//            department.getId(),
//            new DepartmentCreateDto(
//                department.getName(),
//                department.getAbbreviation()
//            )
//        )).isInstanceOf(DepartmentAlreadyExistsByAbbreviationAndCampusIdException.class);
//    }
//
//    @Test
//    public void deleteCampus() {
//        getCampus();
//        getDepartment();
//
//        departmentService.delete(department.getCampus().getId(), department.getId());
//
//        verify(departmentRepository, times(1)).deleteById(department.getId());
//    }
//
//    @Test
//    public void deleteCampusNotFound() {
//        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
//        assertThatThrownBy(() -> departmentService.delete(department.getCampus().getId(), department.getId()))
//            .isInstanceOf(ResourceNotFoundException.class);
//    }
//
//    @Test
//    public void deleteDepartmentNotFound() {
//        getCampus();
//        when(departmentRepository.findByCampusIdAndId(department.getCampus().getId(), department.getId()))
//            .thenReturn(Optional.empty());
//        assertThatThrownBy(() -> departmentService.delete(department.getCampus().getId(), department.getId()))
//            .isInstanceOf(ResourceNotFoundException.class);
//    }
//
//
}
