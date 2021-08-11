package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CampusServiceImpl implements CampusService {
    private final CampusRepository campusRepository;

    @Override
    public Campus create(CampusCreateDto campusCreateDto) {
        if (campusRepository.existsByAbbreviation(campusCreateDto.getAbbreviation())) {
            throw new CampusAlreadyExistsByAbbreviationException(campusCreateDto.getAbbreviation());
        }
        if (campusRepository.existsByInternshipSectorEmail(campusCreateDto.getInternshipSector().getEmail())) {
            throw new CampusAlreadyExistsByEmailException(campusCreateDto.getInternshipSector().getEmail());
        }
        return campusRepository.save(toCampus(campusCreateDto));
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
        Campus campus = getCampus(id);
        if (campusRepository.existsByAbbreviationExcludedId(campusCreateDto.getAbbreviation(), id)) {
            throw new CampusAlreadyExistsByAbbreviationException(campusCreateDto.getAbbreviation());
        }
        if (campusRepository.existsByEmailExcludedId(campusCreateDto.getInternshipSector().getEmail(), id)) {
            throw new CampusAlreadyExistsByEmailException(campusCreateDto.getInternshipSector().getEmail());
        }
        Campus campusUpdated = toCampus(campusCreateDto);
        campusUpdated.setId(id);
        return campusRepository.save(campusUpdated);
    }

    @Override
    public void delete(UUID id) {
        campusRepository.deleteById(getCampus(id).getId());
    }

    private Campus getCampus(UUID id) {
        return campusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceName.CAMPUS, id));
    }

    private Campus toCampus(CampusCreateDto campusCreateDto) {
        return new Campus(
            campusCreateDto.getName(),
            campusCreateDto.getAbbreviation(),
            toAddress(campusCreateDto.getAddress()),
            toInternshipSector(campusCreateDto.getInternshipSector())
        );
    }

    private Address toAddress(AddressDto addressDto) {
        return new Address(
            addressDto.getPostalCode(),
            addressDto.getStreet(),
            addressDto.getNeighborhood(),
            addressDto.getCity(),
            addressDto.getState(),
            addressDto.getNumber(),
            addressDto.getComplement()
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
