package hr.tvz.lbican.studapp.repositories;

import hr.tvz.lbican.studapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStudentsJmbag(String jmbag);
}
