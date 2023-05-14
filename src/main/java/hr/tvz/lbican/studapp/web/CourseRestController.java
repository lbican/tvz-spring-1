package hr.tvz.lbican.studapp.web;

import hr.tvz.lbican.studapp.course.CourseDTO;
import hr.tvz.lbican.studapp.course.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CourseRestController {
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return courseService.findAll();
    }

    @GetMapping("/{jmbag}")
    public List<CourseDTO> getStudentByJMBAG(@PathVariable final String jmbag){
        return courseService.findAllByStudentJmbag(jmbag);
    }
}
