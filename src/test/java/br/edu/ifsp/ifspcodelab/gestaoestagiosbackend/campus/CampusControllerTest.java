//package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;
//
//import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentFactoryUtils;
//import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
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
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class CampusControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private CampusRepository campusRepository;
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @BeforeEach
//    void setUp() {
//        departmentRepository.deleteAll();
//        campusRepository.deleteAll();
//    }
//
//    @Test
//    public void createCampus() throws Exception{
//        MvcResult result = mockMvc.perform(post("/api/v1/campuses")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content("{ " +
//                " \"name\": \"Test Campus\"," +
//                " \"abbreviation\": \"TCS\"," +
//                " \"address\": {" +
//                    " \"postalCode\": \"123456\"," +
//                    " \"street\": \"Test Street\"," +
//                    " \"neighborhood\": \"Test Neighborhood\"," +
//                    " \"city\": \"Test City\"," +
//                    " \"state\": \"Test State\"," +
//                    " \"number\": \"123456\"," +
//                    " \"complement\": \"Test Complement\"" +
//                " }," +
//                " \"internshipSector\": {" +
//                    " \"telephone\": \"1234-5678\"," +
//                    " \"email\": \"testcampus@email.com\"," +
//                    " \"website\": \"https://testcampus.com\"" +
//                " }" +
//            " }")
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isCreated())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.name").value("Test Campus"))
//            .andExpect(jsonPath("$.abbreviation").value("TCS"))
//            .andExpect(jsonPath("$.address.postalCode").value("123456"))
//            .andExpect(jsonPath("$.address.street").value("Test Street"))
//            .andExpect(jsonPath("$.address.neighborhood").value("Test Neighborhood"))
//            .andExpect(jsonPath("$.address.city").value("Test City"))
//            .andExpect(jsonPath("$.address.state").value("Test State"))
//            .andExpect(jsonPath("$.address.number").value("123456"))
//            .andExpect(jsonPath("$.address.complement").value("Test Complement"))
//            .andExpect(jsonPath("$.internshipSector.telephone").value("1234-5678"))
//            .andExpect(jsonPath("$.internshipSector.email").value("testcampus@email.com"))
//            .andExpect(jsonPath("$.internshipSector.website").value("https://testcampus.com"))
//            .andExpect(jsonPath("$.id").exists())
//            .andDo(print())
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void createCampusBadRequest() throws Exception {
//        MvcResult result = mockMvc.perform(post("/api/v1/campuses")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content("{ " +
//                " \"name\": \"Test Campus\"," +
//                " \"abbreviation\": \"TCSS\"," +
//                " \"address\": {" +
//                    " \"postalCode\": \"123456\"," +
//                    " \"street\": \"Test Street\"," +
//                    " \"neighborhood\": \"Test Neighborhood\"," +
//                    " \"city\": \"Test City\"," +
//                    " \"state\": \"Test State\"," +
//                    " \"number\": \"123456\"," +
//                    " \"complement\": \"Test Complement\"" +
//                " }," +
//                " \"internshipSector\": {" +
//                    " \"telephone\": \"1234-5678\"," +
//                    " \"email\": \"testcampus@email.com\"," +
//                    " \"website\": \"https://testcampus.com\"" +
//                " }" +
//            " }")
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
//    public void getCampus() throws Exception {
//        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
//        MvcResult result = mockMvc.perform(get("/api/v1/campuses")
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.*", isA(ArrayList.class)))
//            .andExpect(jsonPath("$.*", hasSize(1)))
//            .andExpect(jsonPath("$[0].id").value(campus.getId().toString()))
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void deleteCampus() throws Exception {
//        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
//
//        MvcResult result = mockMvc.perform(delete("/api/v1/campuses/" + campus.getId())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent())
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void deleteCampusReferentialIntegrityException() throws Exception {
//        Campus campus = campusRepository.save(CampusFactoryUtils.sampleCampus());
//        departmentRepository.save(DepartmentFactoryUtils.sampleDepartment("Department A", "DPA", campus));
//        departmentRepository.save(DepartmentFactoryUtils.sampleDepartment("Department B", "DPB", campus));
//
//        MvcResult result = mockMvc.perform(delete("/api/v1/campuses/" + campus.getId())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isConflict())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.title").value("Referential integrity exception"))
//            .andDo(print())
//            .andReturn();
//
//        assertThat(result).isNotNull();
//    }
//}
