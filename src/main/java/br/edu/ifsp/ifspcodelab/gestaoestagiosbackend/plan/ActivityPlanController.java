package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/advisor-requests/{advisorRequestId}/activity-plans")
@AllArgsConstructor
public class ActivityPlanController {

    private final ActivityPlanService activityPlanService;
    private final ActivityPlanMapper activityPlanMapper;

    @PostMapping
    public ResponseEntity<ActivityPlanDto> create(
        @PathVariable UUID advisorRequestId,
        @RequestPart("file") MultipartFile file
    ) {
        ActivityPlan activityPlan = activityPlanService.create(advisorRequestId, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(activityPlan.getId()).toUri();
        return ResponseEntity.created(uri).body(activityPlanMapper.to(activityPlan));
    }

    @PutMapping("{activityPlanId}")
    public ResponseEntity<ActivityPlanDto> update(
        @PathVariable UUID advisorRequestId,
        @PathVariable UUID activityPlanId,
        @RequestBody ActivityPlan activityPlan
    ) {
        return ResponseEntity.ok(
            activityPlanMapper.to(activityPlanService.update(advisorRequestId, activityPlanId, activityPlan))
        );
    }
}
