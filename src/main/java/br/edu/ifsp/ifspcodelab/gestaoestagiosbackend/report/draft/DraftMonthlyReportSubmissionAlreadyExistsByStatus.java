package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;

public class DraftMonthlyReportSubmissionAlreadyExistsByStatus extends ResourceAlreadyExistsException {
    public DraftMonthlyReportSubmissionAlreadyExistsByStatus(RequestStatus status) {
        super(ResourceName.DRAFT_MONTHLY_REPORT_SUBMISSION, "status", status);
    }
}
