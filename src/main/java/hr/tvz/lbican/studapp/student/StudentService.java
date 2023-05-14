package hr.tvz.lbican.studapp.student;

import hr.tvz.lbican.studapp.student.StudentCommand;
import hr.tvz.lbican.studapp.student.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> findStudents();
    Optional<StudentDTO> findByJMBAG(String jmbag);

    Optional<StudentDTO> save(StudentCommand studentCommand);

    void deleteByJMBAG(String jmbag);
}
