package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentService;
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
public class CampusServiceTest {
    @Mock
    private CampusRepository campusRepository;
    @Mock
    private CityService cityService;
    @Mock
    private DepartmentService departmentService;
    private CampusServiceImpl campusService;

    @BeforeEach
    public void setUp() {
        campusService = new CampusServiceImpl(campusRepository);
        campusService.setCityService(cityService);
        campusService.setDepartmentService(departmentService);
    }

    @Test
    public void createCampus() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);
        Optional<City> optionalCity = Optional.of(city);
        when(campusRepository.save(any(Campus.class))).thenReturn(campus);
        when(cityService.findById(any(UUID.class))).thenReturn(optionalCity);

        Campus campusCreated = campusService.create(sampleCampusCreateDto(campus));

        verify(campusRepository, times(1)).save(any(Campus.class));
        verify(cityService, times(1)).findById(any(UUID.class));

        assertThat(campusCreated).isNotNull();
        assertThat(campusCreated.getId()).isNotNull();
        assertThat(campusCreated.getName()).isEqualTo(campus.getName());
        assertThat(campusCreated.getAbbreviation()).isEqualTo(campus.getAbbreviation());
        assertThat(campusCreated.getInitialRegistrationPattern()).isEqualTo(campus.getInitialRegistrationPattern());
        assertThat(campusCreated.getAddress()).isEqualTo(campus.getAddress());
        assertThat(campusCreated.getInternshipSector()).isEqualTo(campus.getInternshipSector());
    }

    @Test
    public void createCampusAlreadyExistsByEmail() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);

        when(campusRepository.existsByInternshipSectorEmail(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByEmailException.class);
    }

    @Test
    public void createCampusAlreadyExistsByAbbreviation() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);

        when(campusRepository.existsByAbbreviation(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByAbbreviationException.class);
    }

    @Test
    public void createCampusAlreadyExistsByInitialRegistrationPattern() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);

        when(campusRepository.existsByInitialRegistrationPattern(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByInitialRegistrationPatternException.class);
    }

    @Test
    public void createCampusCityIsEmpty() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);

        when(cityService.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void findAll() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);

        when(campusRepository.findAll()).thenReturn(List.of(campus));

        List<Campus> campuses = campusService.findAll();

        assertThat(campuses).hasSize(1);
    }

    @Test
    public void findAllEmpty() {
        when(campusRepository.findAll()).thenReturn(Collections.emptyList());

        List<Campus> campuses = campusService.findAll();

        assertThat(campuses).isEmpty();
    }

    @Test
    public void findById() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);

        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));

        Campus campusFound = campusService.findById(campus.getId());

        assertThat(campusFound).isNotNull();
        assertThat(campusFound.getId()).isEqualTo(campus.getId());
    }

    @Test
    public void findByIdNotFound() {
        State state = StateFactoryUtils.sampleState();
        City city = CityFactoryUtils.sampleCity(state);
        Campus campus = CampusFactoryUtils.sampleCampus(city);

        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> campusService.findById(campus.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }
//
//    @Test
//    public void deleteCampus() {
//        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));
//        when(departmentRepository.countAllByCampusId(any(UUID.class))).thenReturn(0L);
//        campusService.delete(campus.getId());
//        verify(campusRepository, times(1)).deleteById(campus.getId());
//    }
//
//    @Test
//    public void deleteCampusNotFound() {
//        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> campusService.delete(campus.getId()))
//            .isInstanceOf(ResourceNotFoundException.class);
//    }
//
    private CampusCreateDto sampleCampusCreateDto(Campus campus) {
        return new CampusCreateDto(
                campus.getName(),
                campus.getAbbreviation(),
                campus.getInitialRegistrationPattern(),
                AddressCreateDto.builder()
                        .postalCode(campus.getAddress().getPostalCode())
                        .street(campus.getAddress().getStreet())
                        .neighborhood(campus.getAddress().getNeighborhood())
                        .cityId(campus.getAddress().getCity().getId())
                        .number(campus.getAddress().getNumber())
                        .complement(campus.getAddress().getComplement())
                        .build(),
                InternshipSectorDto.builder()
                        .telephone(campus.getInternshipSector().getTelephone())
                        .email(campus.getInternshipSector().getEmail())
                        .website(campus.getInternshipSector().getWebsite())
                        .build()
        );
    }

}
