package hr.tvz.lbican.studapp.controller;

import hr.tvz.lbican.studapp.course.CourseDTO;
import hr.tvz.lbican.studapp.course.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CourseRestControllerTest {

    @InjectMocks
    private CourseRestController courseRestController;

    @Mock
    private CourseService courseService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseRestController).build();
    }

    @WithMockUser
    @Test
    void testGetAllCourses() throws Exception {
        CourseDTO courseDTO = new CourseDTO("Physics", 6);
        when(courseService.findAll()).thenReturn(List.of(courseDTO));

        mockMvc.perform(get("/course")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Physics"))
                .andExpect(jsonPath("$[0].numberOfECTS").value(6));
    }

    @WithMockUser
    @Test
    void testGetStudentByJMBAG() throws Exception {
        CourseDTO courseDTO = new CourseDTO("Physics", 6);
        when(courseService.findAllByStudentJmbag(anyString())).thenReturn(List.of(courseDTO));

        mockMvc.perform(get("/course/12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Physics"))
                .andExpect(jsonPath("$[0].numberOfECTS").value(6));
    }
}
