package hr.tvz.lbican.studapp.service;

import hr.tvz.lbican.studapp.data.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();

    List<CourseDTO> findAllByStudentJmbag(String jmbag);
}
