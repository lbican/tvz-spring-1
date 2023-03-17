package hr.tvz.lbican.studapp.controllers;

import hr.tvz.lbican.studapp.data.StudentDTO;
import hr.tvz.lbican.studapp.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
@AllArgsConstructor
public class StudentRestController {
    private StudentService studentService;

    @GetMapping("all")
    public List<StudentDTO> getAllStudents(){
        return studentService.findStudents();
    }

    @GetMapping("student/{jmbag}")
    public Optional<StudentDTO> getStudent(@PathVariable String jmbag){
        return studentService.getOneStudent(jmbag);
    }
}
