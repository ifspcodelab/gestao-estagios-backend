package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ProblemDetail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.Violation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor

public class CampusController {
    private final CampusService campusService;

    @PostMapping
    public ResponseEntity<CampusDto> create (@Valid @RequestBody CampusCreateDto campusCreateDto) {
        Campus campus = this.campusService.create(campusCreateDto);
        CampusDto campusDto = new CampusDto(
                campus.getId(),
                campus.getName(),
                campus.getAbbreviation(),
                campus.getEmail(),
                campus.getTelephone(),
                campus.getSiteSector());

        return new ResponseEntity<>(campusDto, HttpStatus.CREATED);
    }

        @ExceptionHandler(CampusAlreadyExistsException.class)
        public ResponseEntity<ProblemDetail>  alreadyExists(CampusAlreadyExistsException) {
            return new ResponseEntity<>(
                    new ProblemDetail(
                            "Campus already exists",
                            List.of(new Violation("name", "Campus already exists" + exception.getName()))
                    ),
                    HttpStatus.CONFLICT
            );




}




