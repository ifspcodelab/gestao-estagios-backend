package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Value;

@Value
public class Violation {
    String name;
    String reason;
}
