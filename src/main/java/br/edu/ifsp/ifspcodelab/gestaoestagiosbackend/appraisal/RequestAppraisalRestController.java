package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.appraisal;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/advisor-requests/{requestId}/appraisals")
@AllArgsConstructor
public class RequestAppraisalRestController {
    private final RequestAppraisalService requestAppraisalService;

    @PostMapping()
    public ResponseEntity<RequestAppraisal> create(@PathVariable UUID requestId,
                                                   @RequestBody @Valid RequestAppraisalCreateDto requestAppraisalCreateDto
    ) {
        RequestAppraisal requestAppraisal = requestAppraisalService.create(requestId, requestAppraisalCreateDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{appraisalId}")
            .buildAndExpand(requestAppraisal.getId()).toUri();
        return ResponseEntity.created(uri).body(requestAppraisal);
    }
}
