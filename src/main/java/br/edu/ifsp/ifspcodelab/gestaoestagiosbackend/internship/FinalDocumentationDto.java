package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import lombok.Value;

@Value
public class FinalDocumentationDto {
    byte[] bytes;
    String fileName;

    public FinalDocumentationDto(byte[] bytes, Internship internship) {
        this.bytes = bytes;
        this.fileName = "documentacao-final-" + internship.getStudent().getUser().getRegistration() + ".pdf";
    }
}
