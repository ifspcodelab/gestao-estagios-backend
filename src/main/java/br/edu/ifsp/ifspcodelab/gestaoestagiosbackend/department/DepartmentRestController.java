package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/campuses/{campusId}/departments")
@CrossOrigin
@AllArgsConstructor
public class DepartmentRestController {
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @PostMapping
    public ResponseEntity<DepartmentDto> create(@PathVariable UUID campusId, @Valid @RequestBody DepartmentCreateDto departmentCreateDto) {
        Department department = departmentService.create(campusId, departmentCreateDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{campusId}/{departmentId}")
            .buildAndExpand(department.getCampus().getId(), department.getId()).toUri();
        return ResponseEntity.created(uri).body(departmentMapper.to(department));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> index(@PathVariable UUID campusId) {
        List<DepartmentDto> departments = departmentService.findAll(campusId).stream()
            .map(departmentMapper::to)
            .collect(Collectors.toList());
        return ResponseEntity.ok(departments);
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<DepartmentDto> show(@PathVariable UUID campusId, @PathVariable UUID departmentId) {
        return ResponseEntity.ok(departmentMapper.to(departmentService.findById(campusId, departmentId)));
    }

    @PutMapping("{departmentId}")
    public ResponseEntity<DepartmentDto> update(@PathVariable UUID campusId, @PathVariable UUID departmentId, @Valid @RequestBody DepartmentCreateDto departmentCreateDto) {
        return ResponseEntity.ok(departmentMapper.to(departmentService.update(campusId, departmentId, departmentCreateDto)));
    }

    @DeleteMapping("{departmentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID campusId, @PathVariable UUID departmentId) {
        departmentService.delete(campusId, departmentId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{departmentId}")
    public ResponseEntity<DepartmentDto> patch(
        @PathVariable UUID campusId,
        @PathVariable UUID departmentId,
        @Valid @RequestBody EntityUpdateStatusDto departmentUpdateStatusDto
    ) {
        return ResponseEntity.ok(
            departmentMapper.to(departmentService.setStatus(campusId, departmentId, departmentUpdateStatusDto))
        );
    }

}
