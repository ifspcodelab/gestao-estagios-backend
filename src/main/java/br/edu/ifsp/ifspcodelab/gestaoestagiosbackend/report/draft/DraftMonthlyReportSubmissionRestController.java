package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report.draft;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/internships/{internshipId}/monthly-reports/{monthlyReportId}/drafts")
@AllArgsConstructor
public class DraftMonthlyReportSubmissionRestController {
    private final DraftMonthlyReportSubmissionService draftMonthlyReportSubmissionService;

    @PostMapping
    public ResponseEntity<DraftMonthlyReportSubmission> create(
        @PathVariable UUID internshipId,
        @PathVariable UUID monthlyReportId,
        @RequestPart("file") MultipartFile file
    ) {
        DraftMonthlyReportSubmission draft = draftMonthlyReportSubmissionService.create(internshipId, monthlyReportId, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(draft.getId()).toUri();
        return ResponseEntity.created(uri).body(draft);
    }

    @PutMapping("{draftMonthlyReportSubmissionId}")
    public ResponseEntity<DraftMonthlyReportSubmission> update(
        @PathVariable UUID internshipId,
        @PathVariable UUID monthlyReportId,
        @PathVariable UUID draftMonthlyReportSubmissionId,
        @RequestBody DraftMonthlyReportSubmissionUpdateDto draftMonthlyReportSubmissionUpdateDto
    ) {
        DraftMonthlyReportSubmission draft = draftMonthlyReportSubmissionService.update(
            internshipId,
            monthlyReportId,
            draftMonthlyReportSubmissionId,
            draftMonthlyReportSubmissionUpdateDto
        );
        return ResponseEntity.ok(draft);
    }
}
