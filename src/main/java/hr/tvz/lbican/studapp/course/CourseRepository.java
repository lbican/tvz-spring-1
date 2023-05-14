package hr.tvz.lbican.studapp.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStudentsJmbag(String jmbag);
}
