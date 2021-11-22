package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.report;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/internships/{internshipId}/monthly-reports/{monthlyReportId}/attachments")
@AllArgsConstructor
public class MonthlyReportRestController {
    private final MonthlyReportService monthlyReportService;

    @PutMapping
    public ResponseEntity<MonthlyReport> update(
        @PathVariable UUID internshipId,
        @PathVariable UUID monthlyReportId,
        @RequestPart("file") MultipartFile file
    ) {
        return ResponseEntity.ok(monthlyReportService.sendAttachment(internshipId, monthlyReportId, file));
    }
}
