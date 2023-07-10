package hr.tvz.lbican.studapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CourseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCourses() throws Exception {
        this.mockMvc.perform(
                get("/course")
                        .with(csrf())
                        .with(user("User").password("test").roles("USER"))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getStudentByJMBAG() throws Exception {
        String STUDENT_JMBAG = "0246096016";

        this.mockMvc.perform(
                get("/course/{jmbag}", STUDENT_JMBAG)
                        .with(csrf())
                        .with(user("User").password("test").roles("USER"))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray());

    }
}
