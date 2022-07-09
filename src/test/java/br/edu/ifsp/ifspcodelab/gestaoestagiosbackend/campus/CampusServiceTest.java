package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceReferentialIntegrityException;
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
    private City city;
    private Campus campus;

    @BeforeEach
    public void setUp() {
        campusService = new CampusServiceImpl(campusRepository);
        campusService.setCityService(cityService);
        campusService.setDepartmentService(departmentService);
        State state = StateFactoryUtils.sampleState();
        city = CityFactoryUtils.sampleCity(state);
        campus = CampusFactoryUtils.sampleCampus(city);
    }

    @Test
    public void createCampus() {
        Optional<City> optionalCity = Optional.of(city);
        when(cityService.findById(any(UUID.class))).thenReturn(optionalCity);
        when(campusRepository.save(any(Campus.class))).thenReturn(campus);

        Campus campusCreated = campusService.create(sampleCampusCreateDto(campus));

        verify(cityService, times(1)).findById(any(UUID.class));
        verify(campusRepository, times(1)).save(any(Campus.class));

        assertThat(campusCreated).isEqualTo(campus);
    }

    @Test
    public void createCampusAlreadyExistsByAbbreviation() {
        when(campusRepository.existsByAbbreviation(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByAbbreviationException.class);
    }

    @Test
    public void createCampusAlreadyExistsByInitialRegistrationPattern() {
        when(campusRepository.existsByInitialRegistrationPattern(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByInitialRegistrationPatternException.class);
    }

    @Test
    public void createCampusAlreadyExistsByEmail() {
        when(campusRepository.existsByInternshipSectorEmail(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByEmailException.class);
    }

    @Test
    public void createCampusCityIsEmpty() {
        when(cityService.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void findAll() {
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
    public void findAllByStatus() {
        when(campusRepository.findAllByStatus(any(EntityStatus.class))).thenReturn(List.of(campus));

        List<Campus> campusFound = campusService.findAllByStatus(campus.getStatus());

        assertThat(campusFound).hasSize(1);
    }

    @Test
    public void findAllByStatusIsEmpty() {
        when(campusRepository.findAllByStatus(any(EntityStatus.class))).thenReturn(Collections.emptyList());

        List<Campus> campuses = campusService.findAllByStatus(campus.getStatus());

        assertThat(campuses).isEmpty();
    }

    @Test
    public void findById() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));

        Campus campusFound = campusService.findById(campus.getId());

        assertThat(campusFound).isNotNull();
        assertThat(campusFound.getId()).isEqualTo(campus.getId());
    }

    @Test
    public void findByIdNotFound() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> campusService.findById(campus.getId()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void updateCampus() {

        Optional<City> optionalCity = Optional.of(city);

        when(cityService.findById(any(UUID.class))).thenReturn(optionalCity);
        when(campusRepository.save(any(Campus.class))).thenReturn(campus);
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));

        Campus campusUpdate = campusService.update(campus.getId(), sampleCampusCreateDto(campus));

        verify(cityService, times(1)).findById(any(UUID.class));
        verify(campusRepository, times(1)).save(any(Campus.class));

        assertThat(campusUpdate).isEqualTo(campus);
        assertThat(campusUpdate.getId()).isEqualTo(campus.getId());
    }

    @Test
    public void updateCampusAlreadyExistsByAbbreviationExcludedId() {
        when(campusRepository.existsByAbbreviationExcludedId(any(String.class), any(UUID.class))).thenReturn(true);
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));

        assertThatThrownBy(() -> campusService.update(campus.getId(), sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByAbbreviationException.class);
    }

    @Test
    public void updateCampusAlreadyExistsByInitialRegistrationPatternExcludedId() {
        when(campusRepository.existsByInitialRegistrationPatternExcludedId(any(String.class), any(UUID.class))).thenReturn(true);
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));

        assertThatThrownBy(() -> campusService.update(campus.getId(), sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByInitialRegistrationPatternException.class);
    }

    @Test
    public void setStatus(){
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));
        EntityUpdateStatusDto campusUpdateStatusDto = new EntityUpdateStatusDto();
        campusUpdateStatusDto.setStatus(EntityStatus.ENABLED);

        campusService.setStatus(campus.getId(), campusUpdateStatusDto);

        verify(campusRepository, times (1)).save(campus);
    }

    @Test
    public void setStatusDisabledCallDisableAllByCampusId(){
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));
        EntityUpdateStatusDto campusUpdateStatusDto = new EntityUpdateStatusDto();
        campusUpdateStatusDto.setStatus(EntityStatus.DISABLED);

        campusService.setStatus(campus.getId(), campusUpdateStatusDto);

        verify(departmentService, times(1)).disableAllByCampusId(campus.getId());
        verify(campusRepository, times (1)).save(campus);
    }

    @Test
    public void doNotSetStatusWhenCampusNotFound(){
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        EntityUpdateStatusDto campusUpdateStatusDto = new EntityUpdateStatusDto();
        campusUpdateStatusDto.setStatus(EntityStatus.ENABLED);

        assertThatThrownBy(() -> campusService.setStatus(campus.getId(), campusUpdateStatusDto))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void updateCampusAlreadyExistsByEmailExcludedId() {
        when(campusRepository.existsByEmailExcludedId(any(String.class), any(UUID.class))).thenReturn(true);
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));

        assertThatThrownBy(() -> campusService.update(campus.getId(), sampleCampusCreateDto(campus)))
                .isInstanceOf(CampusAlreadyExistsByEmailException.class);
    }

    @Test
    public void updateCampusCityIsEmpty() {
        when(cityService.findById(any(UUID.class))).thenReturn(Optional.empty());
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));

        assertThatThrownBy(() -> campusService.update(campus.getId(), sampleCampusCreateDto(campus)))
                .isInstanceOf(ResourceNotFoundException.class);
    }


    @Test
    public void deleteCampus() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));

        campusService.delete(campus.getId());

        verify(campusRepository, times(1)).deleteById(campus.getId());
    }

    @Test
    public void deleteCampusDepartmentServiceExistsByCampusId() {
        when(departmentService.existsByCampusId(any(UUID.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.delete(campus.getId()))
                .isInstanceOf(ResourceReferentialIntegrityException.class);
    }

    @Test
    public void deleteCampusNotFound() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> campusService.delete(campus.getId()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void enableCampus() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));
        when(campusRepository.save(any(Campus.class))).thenReturn(campus);
        campus.setStatus(EntityStatus.DISABLED);

        campus = campusService.enable(campus.getId());

        assertThat(campus.getStatus()).isEqualTo(EntityStatus.ENABLED);
    }

    @Test
    public void throwExceptionWhenEnableCampusNotFound() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> campusService.enable(campus.getId()))
                .isInstanceOf(ResourceNotFoundException.class);
    }

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
