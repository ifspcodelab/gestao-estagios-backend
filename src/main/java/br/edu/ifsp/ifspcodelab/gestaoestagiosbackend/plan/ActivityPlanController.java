package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/internships/{internshipId}/activity-plans")
@AllArgsConstructor
public class ActivityPlanController {

    private final ActivityPlanService activityPlanService;
    private final ActivityPlanMapper activityPlanMapper;

    @PostMapping
    public ResponseEntity<ActivityPlanDto> create(
        @PathVariable UUID internshipId,
        @RequestPart("file") MultipartFile file
    ) {
        ActivityPlan activityPlan = activityPlanService.create(internshipId, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(activityPlan.getId()).toUri();
        return ResponseEntity.created(uri).body(activityPlanMapper.to(activityPlan));
    }

    @PutMapping("{activityPlanId}")
    public ResponseEntity<ActivityPlanDto> update(
        @PathVariable UUID internshipId,
        @PathVariable UUID activityPlanId,
        @RequestBody @Valid ActivityPlanUpdateDto activityPlanUpdateDto
    ) {
        return ResponseEntity.ok(
            activityPlanMapper.to(activityPlanService.update(internshipId, activityPlanId, activityPlanUpdateDto))
        );
    }
}
