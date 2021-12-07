package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import lombok.Value;

@Value
public class FinalDocumentationDto {
    byte[] bytes;
    String filename;

    public FinalDocumentationDto(byte[] bytes, Internship internship) {
        this.bytes = bytes;
        this.filename = "documentacao-final-" + internship.getStudent().getUser().getRegistration() + ".pdf";
    }
}
