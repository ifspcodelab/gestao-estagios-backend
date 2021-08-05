package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() { courseRepository.deleteAll(); departmentRepository.deleteAll(); }


    @Test
    public void createCourse() throws Exception {
        Department department = departmentRepository.save(new Department("Test Department"));

        mockMvc.perform(post("/courses")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Test Course\"," +
                    " \"abbreviation\": \"TC\"," +
                    " \"numberOfPeriods\": \"6\"," +
                    " \"departmentId\": " + "\"" + department.getId() + "\"" + " }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Test Course"))
            .andExpect(jsonPath("$.abbreviation").value("TC"))
            .andExpect(jsonPath("$.numberOfPeriods").value(6))
            .andExpect(jsonPath("$.id").exists())
            .andDo(print());
    }

    @Test
    public void createCourseConflictAlreadyExistsForDepartment() throws Exception {
        Department department = departmentRepository.save(new Department("Test Department"));
        courseRepository.save(new Course("Test Course", "TC", 6, department));

        mockMvc.perform(post("/courses")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Test Course\"," +
                    " \"abbreviation\": \"TC\"," +
                    " \"numberOfPeriods\": \"6\"," +
                    " \"departmentId\": " + "\"" + department.getId() + "\"" + " }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Course already exists for department"))
            .andExpect(jsonPath("$.violations[0].name").value("id"))
            .andExpect(jsonPath("$.violations[0].reason").value("Course already exists for department " + department.getId()))
            .andDo(print());
    }

    @Test
    public void createCourseConflictDepartmentNotExists() throws Exception {
        UUID departmentId = UUID.randomUUID();

        mockMvc.perform(post("/courses")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Test Course\"," +
                    " \"abbreviation\": \"TC\"," +
                    " \"numberOfPeriods\": \"6\"," +
                    " \"departmentId\": " + "\"" + departmentId + "\"" + " }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Department not exists"))
            .andExpect(jsonPath("$.violations[0].name").value("id"))
            .andExpect(jsonPath("$.violations[0].reason").value("Department does not exists with id " + departmentId))
            .andDo(print());
    }

    @Test
    public void createCourseBadRequestValidation() throws Exception {
        Department department = departmentRepository.save(new Department("Test Department"));

        mockMvc.perform(post("/courses")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"name\": \"Test Course\"," +
                    " \"abbreviation\": \"TC\"," +
                    " \"numberOfPeriods\": \"0\"," +
                    " \"departmentId\": " + "\"" + department.getId() + "\"" + " }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Your request parameters didn't validate"))
            .andExpect(jsonPath("$.violations[0].name").value("numberOfPeriods"))
            .andExpect(jsonPath("$.violations[0].reason").value("must be greater than or equal to 1"))
            .andDo(print());
    }
}
