package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

@Getter
public class FileMaxSizeException extends RuntimeException {
    private final Integer maxSize;
    private final Long actualSize;

    public FileMaxSizeException(Integer maxSize, Long actualSize) {
        super();
        this.maxSize = maxSize;
        this.actualSize = actualSize;
    }
}
