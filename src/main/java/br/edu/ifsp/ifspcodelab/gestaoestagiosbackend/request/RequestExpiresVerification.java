package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RequestExpiresVerification {

    private static final Logger log = LoggerFactory.getLogger(RequestExpiresVerification.class);
    private AdvisorRequestService advisorRequestService;

    @Autowired
    public void setAdvisorRequestService(AdvisorRequestService advisorRequestService) {
        this.advisorRequestService = advisorRequestService;
    }

    @Scheduled(fixedRateString = "${application.schedule.intervalScanRequestExpiresInMillis}")
    public void verifyExpiredRequests(){
        this.advisorRequestService.verifyExpiredRequests();
        log.info("Verificação de pedidos de orientação de estágio expirados realizada.");
    }
}
