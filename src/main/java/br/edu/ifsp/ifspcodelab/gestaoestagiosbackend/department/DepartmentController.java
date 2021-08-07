package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusNotExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ProblemDetail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.Violation;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

@PostMapping
    public ResponseEntity<DepartmentDto> create(@Valid @RequestBody DepartmentCreateDto departmentCreateDto){
        Department department = departmentService.create(departmentCreateDto);
        return new ResponseEntity<>(new DepartmentDto(department.getId(), department.getName(), department.getAbbreviation()), HttpStatus.CREATED);
    }

    @ExceptionHandler(CampusNotExistsException.class)
    public ResponseEntity<ProblemDetail> notExists(CampusNotExistsException exception) {
        return new ResponseEntity<>(
                new ProblemDetail(
                        "Campus not exists",
                        List.of(new Violation("id", "Campus does not exists with id " + exception.getCampusId()))
                ),
                HttpStatus.CONFLICT
        );
    }
}
