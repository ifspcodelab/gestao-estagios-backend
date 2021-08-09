package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CampusServiceImpl implements CampusService {
    private final CampusRepository campusRepository;

    @Override
    public Campus create(CampusCreateDto campusCreateDto ) {
        if(campusRepository.existsCampusByAbbreviation(campusCreateDto.getAbbreviation())) {
            throw new CampusAlreadyExistsException(campusCreateDto.getAbbreviation());
        }
        return campusRepository.save(
                new Campus(
                        campusCreateDto.getName(),
                        campusCreateDto.getAbbreviation(),
                        campusCreateDto.getEmail(),
                        campusCreateDto.getTelephone(),
                        campusCreateDto.getSiteSector()));
    }
}
