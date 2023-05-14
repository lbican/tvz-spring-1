package hr.tvz.lbican.studapp.controller;

import hr.tvz.lbican.studapp.student.StudentCommand;
import hr.tvz.lbican.studapp.student.StudentDTO;
import hr.tvz.lbican.studapp.student.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("student")
@AllArgsConstructor
public class StudentRestController {
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.findStudents();
        HttpStatus status = students.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;

        return ResponseEntity.status(status).body(students);
    }

    @GetMapping("/{jmbag}")
    public ResponseEntity<StudentDTO> getStudentByJMBAG(@PathVariable final String jmbag){
        return studentService.findByJMBAG(jmbag)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{jmbag}")
    public ResponseEntity<StudentDTO> update(@PathVariable String jmbag, @Valid @RequestBody final StudentCommand updateStudentCommand) {
        return studentService.update(jmbag, updateStudentCommand)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody final StudentCommand command) {
        return studentService.save(command)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{jmbag}")
    public void delete(@PathVariable String jmbag){
        studentService.deleteByJMBAG(jmbag);
    }
}
