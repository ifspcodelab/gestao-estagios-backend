package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.finalsubmission;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/internships/{internshipId}/monthly-reports/{monthlyReportId}/finals")
@AllArgsConstructor
public class finalMonthlyReportSubmissionRestController {
    private final FinalMonthlyReportSubmissionService finalMonthlyReportSubmissionService;

    @PostMapping
    public ResponseEntity<FinalMonthlyReportSubmission> create(
        @PathVariable UUID internshipId,
        @PathVariable UUID monthlyReportId,
        @RequestPart("file") MultipartFile file
    ) {
        FinalMonthlyReportSubmission finalSubmission = finalMonthlyReportSubmissionService.create(internshipId, monthlyReportId, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(finalSubmission.getId()).toUri();
        return ResponseEntity.created(uri).body(finalSubmission);
    }

    @PutMapping("{finalMonthlyReportSubmissionId}/appraisals")
    public ResponseEntity<FinalMonthlyReportSubmission> appraise(
        @PathVariable UUID internshipId,
        @PathVariable UUID monthlyReportId,
        @PathVariable UUID finalMonthlyReportSubmissionId,
        @RequestBody FinalMonthlyReportSubmissionAppraisalDto finalMonthlyReportSubmissionAppraisalDto
    ) {
        FinalMonthlyReportSubmission finalSubmission = finalMonthlyReportSubmissionService.appraise(
            internshipId,
            monthlyReportId,
            finalMonthlyReportSubmissionId,
            finalMonthlyReportSubmissionAppraisalDto
        );
        return ResponseEntity.ok(finalSubmission);
    }
}
