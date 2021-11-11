package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;

import java.util.UUID;

public interface InternshipService {
    Internship create(AdvisorRequest advisorRequest);
    Internship findById(UUID id);
    Internship update(Internship internship);
}
