package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Value;

import java.util.List;

@Value
public class ProblemDetail {
    String title;
    List<Violation> violations;
}