//package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;
//
//import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
//import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
//import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.ArrayList;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.isA;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class DepartmentControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private CampusRepository campusRepository;
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @BeforeEach
//    public void setUp() {
//        departmentRepository.deleteAll();
//        campusRepository.deleteAll();
//    }
//
//    @Test
//    public void createDepartment() throws Exception {
//        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
//        MvcResult result = mockMvc.perform(post("/api/v1/campuses/{campusId}/departments", campus.getId())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content("{ " +
//                " \"name\": \"Test Department\"," +
//                " \"abbreviation\": \"TDP\"" +
//                " }"
//            )
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isCreated())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.name").value("Test Department"))
//            .andExpect(jsonPath("$.abbreviation").value("TDP"))
//            .andDo(print())
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void createDepartmentBadRequest() throws Exception {
//        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
//        MvcResult result = mockMvc.perform(post("/api/v1/campuses/{campusId}/departments", campus.getId())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content("{ " +
//                " \"name\": \"Test Department\"," +
//                " \"abbreviation\": \"TDPP\"" +
//                " }"
//            )
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isBadRequest())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.title").value("Your request parameters didn't validate"))
//            .andExpect(jsonPath("$.violations[0].name").value("abbreviation"))
//            .andExpect(jsonPath("$.violations[0].reason").value("size must be between 3 and 3"))
//            .andDo(print())
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void getDepartment() throws Exception {
//        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
//        Department department = DepartmentFactoryUtils.sampleDepartment();
//        department.getCampus().setId(campus.getId());
//        departmentRepository.save(department);
//
//        MvcResult result = mockMvc.perform(get("/api/v1/campuses/{campusId}/departments", campus.getId())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.*", isA(ArrayList.class)))
//            .andExpect(jsonPath("$.*", hasSize(1)))
//            .andExpect(jsonPath("$[0].id").value(department.getId().toString()))
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void getDepartmentEmpty() throws Exception {
//        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
//
//        MvcResult result = mockMvc.perform(get("/api/v1/campuses/{campusId}/departments", campus.getId())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.*", isA(ArrayList.class)))
//            .andExpect(jsonPath("$.*", hasSize(0)))
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void getDepartmentById() throws Exception {
//        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
//        Department department = DepartmentFactoryUtils.sampleDepartment();
//        department.getCampus().setId(campus.getId());
//        departmentRepository.save(department);
//
//        MvcResult result = mockMvc.perform(
//            get("/api/v1/campuses/{campusId}/departments/{departmentId}", campus.getId(), department.getId())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.id").value(department.getId().toString()))
//            .andExpect(jsonPath("$.name").value(department.getName()))
//            .andExpect(jsonPath("$.abbreviation").value(department.getAbbreviation()))
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//}
