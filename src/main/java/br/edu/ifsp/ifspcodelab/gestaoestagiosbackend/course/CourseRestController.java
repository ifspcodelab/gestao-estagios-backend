package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/courses")
@CrossOrigin
@AllArgsConstructor
public class CourseRestController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CourseCreateDto courseCreateDto) {
        Course course = courseService.create(courseCreateDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseId}")
            .buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(courseMapper.to(course));
    }
}
