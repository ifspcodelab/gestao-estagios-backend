package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.FileExtensionException;
import lombok.Getter;

@Getter
public class FileExtensionPdfException extends FileExtensionException {
    public FileExtensionPdfException(String fileExtension) {
        super(fileExtension);
    }
}
