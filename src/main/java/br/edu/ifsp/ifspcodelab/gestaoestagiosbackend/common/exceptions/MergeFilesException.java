package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import lombok.Getter;

@Getter
public class MergeFilesException extends RuntimeException {
    private final String exceptionMessage;

    public MergeFilesException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
