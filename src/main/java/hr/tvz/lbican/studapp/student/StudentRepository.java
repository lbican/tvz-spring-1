package hr.tvz.lbican.studapp.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByJmbag(String jmbag);

    void deleteByJmbag(String jmbag);
}
