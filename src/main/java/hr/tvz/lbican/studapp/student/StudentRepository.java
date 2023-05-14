package hr.tvz.lbican.studapp.student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findStudentByJMBAG(String jmbag);
    Optional<Student> save(Student student);

    Optional<Student> update(String JMBAG, Student updatedStudent);

    void deleteStudentByJMBAG(String jmbag);
}
