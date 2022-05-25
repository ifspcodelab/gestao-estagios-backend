package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceReferentialIntegrityException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CampusServiceImpl implements CampusService {
    private CampusRepository campusRepository;

    private CityService cityService;
    private DepartmentService departmentService;

    public CampusServiceImpl(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public Campus create(CampusCreateDto campusCreateDto) {
        if (campusRepository.existsByAbbreviation(campusCreateDto.getAbbreviation())) {
            throw new CampusAlreadyExistsByAbbreviationException(campusCreateDto.getAbbreviation());
        }
        if (campusRepository.existsByInitialRegistrationPattern(campusCreateDto.getInitialRegistrationPattern())){
            throw new CampusAlreadyExistsbyInitialRegistrationPatternException(campusCreateDto.getInitialRegistrationPattern());
        }
        if (campusRepository.existsByInternshipSectorEmail(campusCreateDto.getInternshipSector().getEmail())) {
            throw new CampusAlreadyExistsByEmailException(campusCreateDto.getInternshipSector().getEmail());
        }
        UUID cityId = campusCreateDto.getAddress().getCityId();
        Optional<City> cityOptional = cityService.findById(cityId);
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
    public List<Campus> findAllByStatus(EntityStatus status) {
        return campusRepository.findAllByStatus(status);
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
        if (campusRepository.existsByInitialRegistrationPattern(campusCreateDto.getInitialRegistrationPattern())){
            throw new CampusAlreadyExistsbyInitialRegistrationPatternException(campusCreateDto.getInitialRegistrationPattern());
        }
        if (campusRepository.existsByEmailExcludedId(campusCreateDto.getInternshipSector().getEmail(), id)) {
            throw new CampusAlreadyExistsByEmailException(campusCreateDto.getInternshipSector().getEmail());
        }
        UUID cityId = campusCreateDto.getAddress().getCityId();
        Optional<City> cityOptional = cityService.findById(cityId);
        if (cityOptional.isEmpty()){
            throw new ResourceNotFoundException(ResourceName.CITY, cityId);
        }
        Campus campusUpdated = toCampus(campusCreateDto, cityOptional.get());
        campusUpdated.setId(id);
        return campusRepository.save(campusUpdated);
    }

    @Transactional
    @Override
    public Campus setStatus(UUID id, EntityUpdateStatusDto campusUpdateStatusDto) {
        Campus campusUpdated = getCampus(id);

        campusUpdated.setStatus(campusUpdateStatusDto.getStatus());

        if (campusUpdated.getStatus() == EntityStatus.DISABLED) {
            departmentService.disableAllByCampusId(id);
        }
        return campusRepository.save(campusUpdated);
    }

    @Override
    public void delete(UUID id) {
        if (departmentService.existsByCampusId(id)) {
            throw new ResourceReferentialIntegrityException(ResourceName.DEPARTMENT, ResourceName.CAMPUS);
        }
        campusRepository.deleteById(getCampus(id).getId());
    }

    @Override
    public Campus enable(UUID id) {
        return campusRepository.save(getCampus(id).enable());
    }

    private Campus getCampus(UUID id) {
        return campusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceName.CAMPUS, id));
    }

    private Campus toCampus(CampusCreateDto campusCreateDto, City city) {
        return new Campus(
            campusCreateDto.getName(),
            campusCreateDto.getAbbreviation(),
            campusCreateDto.getInitialRegistrationPattern(),
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
