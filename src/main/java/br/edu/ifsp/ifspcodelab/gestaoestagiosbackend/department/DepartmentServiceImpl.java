package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusRepository;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final CampusRepository campusRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;

    @Override
    public Department create(UUID campusId, DepartmentCreateDto departmentCreateDto) {
        Campus campus = getCampus(campusId);
        if (departmentRepository.existsByAbbreviationAndCampusId(
            departmentCreateDto.getAbbreviation(),
            campusId)) {
            throw new DepartmentAlreadyExistsByAbbreviationAndCampusIdException(
                departmentCreateDto.getAbbreviation(),
                campusId
            );
        }
        return departmentRepository.save(
            new Department(departmentCreateDto.getName(), departmentCreateDto.getAbbreviation(), campus)
        );
    }

    @Override
    public List<Department> findAll(UUID campusId) {
        getCampus(campusId);
        return departmentRepository.findAllByCampusId(campusId);
    }

    @Override
    public Department findById(UUID campusId, UUID departmentId) {
        getCampus(campusId);
        return departmentRepository.findByCampusIdAndId(campusId, departmentId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.DEPARTMENT, departmentId)
        );
    }

    @Override
    public Department update(UUID campusId, UUID departmentId, DepartmentCreateDto departmentCreateDto) {
        Campus campus = getCampus(campusId);
        getDepartment(campusId, departmentId);
        if (departmentRepository.existsByAbbreviationAndCampusIdExcludedId(
            departmentCreateDto.getAbbreviation(),
            campusId,
            departmentId)) {
            throw new DepartmentAlreadyExistsByAbbreviationAndCampusIdException(
                departmentCreateDto.getAbbreviation(),
                campusId
            );
        }
        Department departmentUpdated = new Department(departmentCreateDto.getName(), departmentCreateDto.getAbbreviation(), campus);
        departmentUpdated.setId(departmentId);
        return departmentRepository.save(departmentUpdated);
    }

    @Override
    public Department setStatus(UUID campusId, UUID id, EntityUpdateStatusDto departmentUpdateStatusDto) {
        getCampus(campusId);
        Department departmentUpdated = getDepartment(campusId, id);

        departmentUpdated.setStatus(departmentUpdateStatusDto.getStatus());

        if (departmentUpdated.getStatus() == EntityStatus.DISABLED) {
            courseRepository.disableAllByDepartmentId(id);
        }
        return departmentRepository.save(departmentUpdated);
    }

    @Override
    public void delete(UUID campusId, UUID departmentId) {
        getCampus(campusId);
        getDepartment(campusId, departmentId);
        departmentRepository.deleteById(departmentId);
    }

    private Campus getCampus(UUID campusId) {
        return campusRepository
            .findById(campusId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CAMPUS, campusId));
    }

    private Department getDepartment(UUID campusId, UUID departmentId) {
        return departmentRepository.findByCampusIdAndId(campusId, departmentId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.DEPARTMENT, departmentId)
        );
    }
}
