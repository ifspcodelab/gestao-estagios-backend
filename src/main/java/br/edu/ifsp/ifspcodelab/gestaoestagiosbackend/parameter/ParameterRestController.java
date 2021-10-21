package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.parameter;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/parameters")
@AllArgsConstructor
public class ParameterRestController {
    private final ParameterService parameterService;
    private final ParameterMapper parameterMapper;

    @GetMapping
    public ResponseEntity<ParameterDto> index() {
        return ResponseEntity.ok(parameterMapper.to(parameterService.findFirst()));
    }

    @PutMapping
    public ResponseEntity<ParameterDto> update(@Valid @RequestBody ParameterCreateDto parameterCreateDto) {
        return ResponseEntity.ok(parameterMapper.to(parameterService.update(parameterCreateDto)));
    }
}
