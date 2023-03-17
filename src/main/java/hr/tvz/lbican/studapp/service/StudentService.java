package hr.tvz.lbican.studapp.service;

import hr.tvz.lbican.studapp.data.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> findStudents();
    Optional<StudentDTO> getOneStudent(String jmbag);
}
