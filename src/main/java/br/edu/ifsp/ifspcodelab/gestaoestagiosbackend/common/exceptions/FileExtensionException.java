package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

@Getter
public class FileExtensionException extends RuntimeException {
    private final String expectedFileExtension;
    private final String actualFileExtension;

    public FileExtensionException(String expectedFileExtension, String actualFileExtension) {
        super();
        this.expectedFileExtension = expectedFileExtension;
        this.actualFileExtension = actualFileExtension;
    }
}
