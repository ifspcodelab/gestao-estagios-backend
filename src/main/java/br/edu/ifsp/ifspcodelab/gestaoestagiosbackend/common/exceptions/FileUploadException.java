package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

@Getter
public class FileUploadException extends RuntimeException {
    private final String fileName;

    public FileUploadException(String fileName) {
        super();
        this.fileName = fileName;
    }
}
