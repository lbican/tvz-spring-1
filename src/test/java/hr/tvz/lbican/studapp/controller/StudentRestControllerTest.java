package hr.tvz.lbican.studapp.controller;

import hr.tvz.lbican.studapp.student.StudentDTO;
import hr.tvz.lbican.studapp.student.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StudentRestControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentRestController studentRestController;

    private MockMvc mockMvc;

    @Test
    void getAllStudentsTest() throws Exception {
        when(studentService.findStudents()).thenReturn(Collections.singletonList(new StudentDTO("1234567890", "John", "Doe", 120, true)));

        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController).build();
        mockMvc.perform(get("/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jmbag").value("1234567890"))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].numberOfECTS").value(120));
    }

    @Test
    void getStudentByJMBAGTest() throws Exception {
        when(studentService.findByJMBAG(anyString())).thenReturn(Optional.of(new StudentDTO("1234567890", "John", "Doe", 120, true)));

        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController).build();
        mockMvc.perform(get("/student/1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jmbag").value("1234567890"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.numberOfECTS").value(120));
    }

    @Test
    void getEmptyStudentByJmbagTest() throws Exception {
        when(studentService.findByJMBAG(anyString())).thenReturn(Optional.empty());

        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController).build();
        mockMvc.perform(get("/student/1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext
    void saveStudentTest() throws Exception {
        when(studentService.save(any())).thenReturn(Optional.of(new StudentDTO("1234567890", "John", "Doe", 120, true)));

        String jsonStudentCommand = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"jmbag\": \"1234567890\", \"dateOfBirth\": \"01.01.2000.\", \"numberOfECTS\": 120 }";

        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController).build();
        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStudentCommand))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.jmbag").value("1234567890"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.numberOfECTS").value(120));
    }

    @Test
    @DirtiesContext
    void saveStudentConflictTest() throws Exception {
        when(studentService.save(any())).thenReturn(Optional.empty());

        String jsonStudentCommand = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"jmbag\": \"1234567890\", \"dateOfBirth\": \"01.01.2000.\", \"numberOfECTS\": 120 }";

        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController).build();
        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStudentCommand))
                .andExpect(status().isConflict());
    }


    @Test
    @DirtiesContext
    void updateStudentTest() throws Exception {
        when(studentService.update(anyString(), any())).thenReturn(Optional.of(new StudentDTO("1234567890", "John", "Doe", 120, true)));

        String jsonStudentCommand = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"jmbag\": \"1234567890\", \"dateOfBirth\": \"01.01.2000.\", \"numberOfECTS\": 120 }";

        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController).build();
        mockMvc.perform(put("/student/1234567890")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStudentCommand))
                .andExpect(jsonPath("$.jmbag").value("1234567890"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.numberOfECTS").value(120));
    }

    @Test
    @DirtiesContext
    void deleteStudentTest() throws Exception {
        doNothing().when(studentService).deleteByJMBAG(anyString());

        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController).build();
        mockMvc.perform(delete("/student/1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
