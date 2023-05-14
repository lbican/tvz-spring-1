package hr.tvz.lbican.studapp.student;

import hr.tvz.lbican.studapp.student.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findStudentByJMBAG(String jmbag);
    Optional<Student> save(Student student);

    void deleteStudentByJMBAG(String jmbag);
}
