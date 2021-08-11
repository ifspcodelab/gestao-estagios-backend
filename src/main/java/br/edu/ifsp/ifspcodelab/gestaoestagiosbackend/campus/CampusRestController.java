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
@RequestMapping("api/v1/campuses")
@AllArgsConstructor
public class CampusRestController {
    private final CampusService campusService;

    @PostMapping
    public ResponseEntity<CampusDto> create(@Valid @RequestBody CampusCreateDto campusCreateDto) {
        Campus campus = campusService.create(campusCreateDto);
        return new ResponseEntity<CampusDto>(new CampusDto(campus), HttpStatus.CREATED);
    }

    @ExceptionHandler(CampusAlreadyExistsByAbbreviationException.class)
    public ResponseEntity<ProblemDetail> alreadyExistsByAbbreviation(CampusAlreadyExistsByAbbreviationException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                "Campus already exists",
                List.of(new Violation("abbreviation", "Campus already exists with abbreviation " + exception.getAbbreviation()))
            ),
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(CampusAlreadyExistsByEmailException.class)
    public ResponseEntity<ProblemDetail> alreadyExists(CampusAlreadyExistsByEmailException exception) {
        return new ResponseEntity<>(
            new ProblemDetail(
                "Campus already exists",
                List.of(new Violation("email", "Campus already exists with email " + exception.getEmail()))
            ),
            HttpStatus.CONFLICT
        );
    }
}
