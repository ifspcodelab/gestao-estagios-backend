package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/states")
@AllArgsConstructor
public class StateRestController {
    private StateRepository stateRepository;

    @GetMapping
    public ResponseEntity<List<State>> index() {
        List<State> states = stateRepository.findAll();
        return ResponseEntity.ok(states);
    }
}
