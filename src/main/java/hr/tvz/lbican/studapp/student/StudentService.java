package hr.tvz.lbican.studapp.student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> findStudents();
    Optional<StudentDTO> findByJMBAG(String jmbag);

    Optional<StudentDTO> save(StudentCommand studentCommand);

    Optional<StudentDTO> update(String JMBAG, StudentCommand updatedStudentCommand);

    void deleteByJMBAG(String jmbag);
}
