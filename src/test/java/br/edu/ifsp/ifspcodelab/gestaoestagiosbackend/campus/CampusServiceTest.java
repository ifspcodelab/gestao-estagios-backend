package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

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
public class CampusServiceTest {
    @Mock
    private CampusRepository campusRepository;
    @InjectMocks
    private CampusServiceImpl campusService;

    private Campus campus;

    @BeforeEach
    public void setUp() {
        campus = CampusFactory.sampleCampus();
    }

    @Test
    public void createCampus() {
        when(campusRepository.save(any(Campus.class))).thenReturn(CampusFactory.sampleCampus());

        Campus campusCreated = campusService.create(sampleCampusCreateDto(campus));

        verify(campusRepository, times(1)).save(any(Campus.class));

        assertThat(campusCreated).isNotNull();
        assertThat(campusCreated.getId()).isNotNull();
        assertThat(campusCreated.getName()).isEqualTo(campus.getName());
        assertThat(campusCreated.getAbbreviation()).isEqualTo(campus.getAbbreviation());
        assertThat(campusCreated.getAddress()).isEqualTo(campus.getAddress());
        assertThat(campusCreated.getInternshipSector()).isEqualTo(campus.getInternshipSector());
    }

    @Test
    public void createCampusAlreadyExistsByEmail() {
        when (campusRepository.existsByInternshipSectorEmail(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
            .isInstanceOf(CampusAlreadyExistsByEmailException.class);
    }

    @Test
    public void createCampusAlreadyExistsByAbbreviation() {
        when (campusRepository.existsByAbbreviation(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> campusService.create(sampleCampusCreateDto(campus)))
            .isInstanceOf(CampusAlreadyExistsByAbbreviationException.class);
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
    public void deleteCampus() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.of(campus));
        campusService.delete(campus.getId());
        verify(campusRepository, times(1)).deleteById(campus.getId());
    }

    @Test
    public void deleteCampusNotFound() {
        when(campusRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> campusService.delete(campus.getId()))
            .isInstanceOf(ResourceNotFoundException.class);
    }

    private CampusCreateDto sampleCampusCreateDto(Campus campus) {
        return new CampusCreateDto(
            campus.getName(),
            campus.getAbbreviation(),
            new AddressDto(campus.getAddress()),
            new InternshipSectorDto(campus.getInternshipSector())
        );
    }

}
