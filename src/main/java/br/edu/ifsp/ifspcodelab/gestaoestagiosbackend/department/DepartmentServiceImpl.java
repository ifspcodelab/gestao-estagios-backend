package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusNotExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final CampusRepository campusRepository;

    @Override
    public Department create(DepartmentCreateDto departmentCreateDto) {
        Campus campus = campusRepository
                .findById(departmentCreateDto.getCampusId())
                .orElseThrow(()-> new CampusNotExistsException(departmentCreateDto.getCampusId()));

        return this.departmentRepository.save(
                new Department(
                    departmentCreateDto.getName(),
                    departmentCreateDto.getAbbreviation(),
                    campus
                )
        );
    }
}
