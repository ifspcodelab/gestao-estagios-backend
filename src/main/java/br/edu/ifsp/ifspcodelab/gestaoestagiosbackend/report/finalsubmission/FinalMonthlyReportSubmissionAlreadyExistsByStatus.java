package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;

public class FinalMonthlyReportSubmissionAlreadyExistsByStatus extends ResourceAlreadyExistsException {
    public FinalMonthlyReportSubmissionAlreadyExistsByStatus(RequestStatus status) {
        super(ResourceName.FINAL_MONTHLY_REPORT_SUBMISSION, "status", status);
    }
}
