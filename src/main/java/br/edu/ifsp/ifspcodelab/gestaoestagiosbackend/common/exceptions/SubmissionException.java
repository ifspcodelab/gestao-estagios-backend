package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.ReportStatus;
import lombok.Getter;

@Getter
public class SubmissionException extends RuntimeException {
    private ReportStatus status;

    public SubmissionException(ReportStatus status) {
        super();
        this.status = status;
    }
}
