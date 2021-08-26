package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityRepository;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceReferentialIntegrityException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CampusServiceImpl implements CampusService {
    private final CampusRepository campusRepository;
    private final CityRepository cityRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Campus create(CampusCreateDto campusCreateDto) {
        if (campusRepository.existsByAbbreviation(campusCreateDto.getAbbreviation())) {
            throw new CampusAlreadyExistsByAbbreviationException(campusCreateDto.getAbbreviation());
        }
        if (campusRepository.existsByInternshipSectorEmail(campusCreateDto.getInternshipSector().getEmail())) {
            throw new CampusAlreadyExistsByEmailException(campusCreateDto.getInternshipSector().getEmail());
        }
        UUID cityId = campusCreateDto.getAddress().getCityId();
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isEmpty()){
            throw new ResourceNotFoundException(ResourceName.CITY, cityId);
        }
        return campusRepository.save(toCampus(campusCreateDto, cityOptional.get()));
    }

    @Override
    public List<Campus> findAll() {
        return campusRepository.findAll();
    }

    @Override
    public Campus findById(UUID id) {
        return getCampus(id);
    }

    @Override
    public Campus update(UUID id, CampusCreateDto campusCreateDto) {
        getCampus(id);
        if (campusRepository.existsByAbbreviationExcludedId(campusCreateDto.getAbbreviation(), id)) {
            throw new CampusAlreadyExistsByAbbreviationException(campusCreateDto.getAbbreviation());
        }
        if (campusRepository.existsByEmailExcludedId(campusCreateDto.getInternshipSector().getEmail(), id)) {
            throw new CampusAlreadyExistsByEmailException(campusCreateDto.getInternshipSector().getEmail());
        }
        UUID cityId = campusCreateDto.getAddress().getCityId();
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isEmpty()){
            throw new ResourceNotFoundException(ResourceName.CITY, cityId);
        }
        Campus campusUpdated = toCampus(campusCreateDto, cityOptional.get());
        campusUpdated.setId(id);
        return campusRepository.save(campusUpdated);
    }

    @Override
    public void delete(UUID id) {
        if (departmentRepository.countAllByCampusId(id) != 0) {
            throw new ResourceReferentialIntegrityException(ResourceName.DEPARTMENT, ResourceName.CAMPUS);
        }
        campusRepository.deleteById(getCampus(id).getId());
    }

    private Campus getCampus(UUID id) {
        return campusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceName.CAMPUS, id));
    }

    private Campus toCampus(CampusCreateDto campusCreateDto, City city) {
        return new Campus(
            campusCreateDto.getName(),
            campusCreateDto.getAbbreviation(),
            toAddress(campusCreateDto.getAddress(), city),
            toInternshipSector(campusCreateDto.getInternshipSector())
        );
    }

    private Address toAddress(AddressCreateDto addressDto, City city) {
        return new Address(
            addressDto.getPostalCode(),
            addressDto.getStreet(),
            addressDto.getNeighborhood(),
            addressDto.getNumber(),
            addressDto.getComplement(),
            city
        );
    }

    private InternshipSector toInternshipSector(InternshipSectorDto internshipSectorDto) {
        return new InternshipSector(
            internshipSectorDto.getTelephone(),
            internshipSectorDto.getEmail(),
            internshipSectorDto.getWebsite()
        );
    }
}
