package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.appraisal;

import java.util.UUID;

public interface RequestAppraisalService {
    RequestAppraisal create(UUID requestId, RequestAppraisalCreateDto requestAppraisalCreateDto);
}
