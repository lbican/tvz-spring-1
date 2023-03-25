package hr.tvz.lbican.studapp.repositories;

import hr.tvz.lbican.studapp.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findStudentByJMBAG(String jmbag);
    Optional<Student> save(Student student);

    void deleteStudentByJMBAG(String jmbag);
}
