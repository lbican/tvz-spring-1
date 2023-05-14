package hr.tvz.lbican.studapp.course;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;


    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map(this::mapCourseToDTO).toList();
    }

    @Override
    public List<CourseDTO> findAllByStudentJmbag(String jmbag) {
        return courseRepository.findByStudentsJmbag(jmbag).stream().map(this::mapCourseToDTO).toList();
    }

    private CourseDTO mapCourseToDTO(Course course){
        return new CourseDTO(course.getName(), course.getNumberOfECTS());
    }
}
