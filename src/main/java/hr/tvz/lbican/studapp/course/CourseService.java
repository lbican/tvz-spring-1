package hr.tvz.lbican.studapp.course;

import hr.tvz.lbican.studapp.course.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();

    List<CourseDTO> findAllByStudentJmbag(String jmbag);
}
