package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

@Getter
public class FileExtensionException extends RuntimeException {
    private final String fileExtension;

    public FileExtensionException(String fileExtension) {
        super();
        this.fileExtension = fileExtension;
    }
}
