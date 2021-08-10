package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CampusServiceImpl implements CampusService {
    private final CampusRepository campusRepository;

    @Override
    public Campus create(CampusCreateDto campusCreateDto) {
        return campusRepository.save(toCampus(campusCreateDto));
    }

    private Campus toCampus(CampusCreateDto campusCreateDto) {
        return new Campus(
            campusCreateDto.getName(),
            campusCreateDto.getAbbreviation(),
            toAddress(campusCreateDto.getAddress()),
            campusCreateDto.getTelephone(),
            campusCreateDto.getEmail(),
            campusCreateDto.getWebsite()
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
}
