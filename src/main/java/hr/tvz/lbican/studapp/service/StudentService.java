package hr.tvz.lbican.studapp.service;

import hr.tvz.lbican.studapp.command.StudentCommand;
import hr.tvz.lbican.studapp.data.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> findStudents();
    Optional<StudentDTO> findByJMBAG(String jmbag);

    Optional<StudentDTO> save(StudentCommand studentCommand);

    void deleteByJMBAG(String jmbag);
}
