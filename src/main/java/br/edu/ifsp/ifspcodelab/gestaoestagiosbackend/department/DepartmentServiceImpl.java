package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceReferentialIntegrityException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    private CampusService campusService;
    private CourseService courseService;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Autowired
    public void setCampusService(CampusService campusService) {
        this.campusService = campusService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Department create(UUID campusId, DepartmentCreateDto departmentCreateDto) {
        Campus campus = campusService.findById(campusId);

        if (campus.getStatus() == EntityStatus.DISABLED) {
            throw new ResourceReferentialIntegrityException(ResourceName.DEPARTMENT, ResourceName.CAMPUS);
        }

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
        campusService.findById(campusId);
        return departmentRepository.findAllByCampusId(campusId);
    }

    @Override
    public Department findById(UUID departmentId) {
        return departmentRepository.findById(departmentId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.DEPARTMENT, departmentId)
        );
    }

    @Override
    public Department findByCampusIdAndId(UUID campusId, UUID departmentId) {
        campusService.findById(campusId);
        return departmentRepository.findByCampusIdAndId(campusId, departmentId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.DEPARTMENT, departmentId)
        );
    }

    @Override
    public Department update(UUID campusId, UUID departmentId, DepartmentCreateDto departmentCreateDto) {
        Campus campus = campusService.findById(campusId);
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
    @Transactional
    public Department setStatus(UUID campusId, UUID id, EntityUpdateStatusDto departmentUpdateStatusDto) {
        campusService.findById(campusId);
        Department departmentUpdated = getDepartment(campusId, id);

        departmentUpdated.setStatus(departmentUpdateStatusDto.getStatus());

        if (departmentUpdated.getStatus() == EntityStatus.DISABLED) {
            courseService.disableAllByDepartmentId(id);
        }
        return departmentRepository.save(departmentUpdated);
    }

    @Override
    public void delete(UUID campusId, UUID departmentId) {
        campusService.findById(campusId);
        getDepartment(campusId, departmentId);
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public boolean existsByCampusId(UUID campusId) {
        return departmentRepository.existsByCampusId(campusId);
    }

    @Override
    public void disableAllByCourseId(UUID courseId) {
        departmentRepository.disableAllByCourseId(courseId);
    }

    private Department getDepartment(UUID campusId, UUID departmentId) {
        return departmentRepository.findByCampusIdAndId(campusId, departmentId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.DEPARTMENT, departmentId)
        );
    }
}
