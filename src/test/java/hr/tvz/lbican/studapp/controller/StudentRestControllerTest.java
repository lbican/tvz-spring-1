package hr.tvz.lbican.studapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.lbican.studapp.student.StudentCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
class StudentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllStudents() throws Exception {
        this.mockMvc.perform(
                get("/student")
                        .with(user("Admin").password("test").roles("ADMIN"))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getNotExistingStudentByJMBAG() throws Exception {
        String JMBAG_TO_FETCH = "0246096030";

        this.mockMvc.perform(
                get("/student/{jmbag}", JMBAG_TO_FETCH)
                        .with(user("User").password("user").roles("USER"))
                        .with(csrf())
        ).andExpect(status().isNotFound());
    }

    @Test
    void updateNonExistingStudent() throws Exception{
        StudentCommand studentCommand = new StudentCommand("Luka", "Bićan", "0246096030", LocalDate.now().minusYears(21), 180);

        this.mockMvc.perform(
                put("/student/{jmbag}", studentCommand.getJmbag())
                        .with(user("Admin").password("test").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentCommand))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    void updateExistingStudent() throws Exception{
        StudentCommand studentCommand = new StudentCommand("Lukas", "Bićan", "0246096016", LocalDate.now().minusYears(21), 180);

        this.mockMvc.perform(
                put("/student/{jmbag}", studentCommand.getJmbag())
                        .with(user("Admin").password("test").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(studentCommand.getFirstName()));
    }

    @Test
    void saveNewStudent() throws Exception {
        StudentCommand studentCommand = new StudentCommand("Luka", "Bićan", "0246096030", LocalDate.now().minusYears(21), 180);

        this.mockMvc.perform(
                        post("/student")
                                .with(user("Admin").password("test").roles("ADMIN"))
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studentCommand))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(studentCommand.getJmbag()))
                .andExpect(jsonPath("$.firstName").value(studentCommand.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(studentCommand.getLastName()));
    }

    @Test
    void saveExistingStudent() throws Exception {
        StudentCommand studentCommand = new StudentCommand("Luka", "Bićan", "0246096016", LocalDate.now().minusYears(21), 180);

        this.mockMvc.perform(
                        post("/student")
                                .with(user("Admin").password("test").roles("ADMIN"))
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studentCommand))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isConflict());
    }

    @Test
    void deleteStudentWithAdminPrivileges() throws Exception {
        String JMBAG_TO_DELETE = "0246096052";

        this.mockMvc.perform(
                delete("/student/{jmbag}", JMBAG_TO_DELETE)
                        .with(user("Admin").password("test").roles("ADMIN"))
                .with(csrf())
        ).andExpect(status().isNoContent());
    }

    @Test
    void deleteStudentWithUserPrivileges() throws Exception {
        String JMBAG_TO_DELETE = "0246096052";

        this.mockMvc.perform(
                delete("/student/{jmbag}", JMBAG_TO_DELETE)
                        .with(user("User").password("user").roles("USER"))
                        .with(csrf())
        ).andExpect(status().isForbidden());
    }
}
