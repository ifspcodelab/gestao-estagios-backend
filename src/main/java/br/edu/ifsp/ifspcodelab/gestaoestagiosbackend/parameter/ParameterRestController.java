package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/parameters")
@AllArgsConstructor
public class ParameterRestController {
    private final ParameterService parameterService;
    private final ParameterMapper parameterMapper;

    @GetMapping
    public ResponseEntity<List<ParameterDto>> index() {
        List<ParameterDto> parameters = parameterService.findAll().stream()
                .map(parameterMapper::to)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parameters);
    }

    @PutMapping
    public ResponseEntity<ParameterDto> update(@Valid @RequestBody ParameterCreateDto parameterCreateDto) {
        return ResponseEntity.ok(parameterMapper.to(parameterService.update(parameterCreateDto)));
    }
}
