package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequest;

public interface InternshipService {
    Internship create(AdvisorRequest advisorRequest);
}
