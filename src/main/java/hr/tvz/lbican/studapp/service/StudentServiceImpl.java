package hr.tvz.lbican.studapp.service;

import hr.tvz.lbican.studapp.data.StudentDTO;
import hr.tvz.lbican.studapp.models.Student;
import hr.tvz.lbican.studapp.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    private static final int YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED = 26;

    @Override
    public List<StudentDTO> findStudents() {
        return studentRepository.findAll().stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> getOneStudent(String jmbag) {

        return studentRepository.findStudentByJmbag(jmbag).map(this::mapStudentToDTO);
    }

    private StudentDTO mapStudentToDTO(final Student student){
        return new StudentDTO(student.getName(), student.getSurname(), this.shouldTuitionBePayed(student.getDateOfBirth()));
    }

    private boolean shouldTuitionBePayed(LocalDate dateOfBirth){
        return dateOfBirth.plusYears(YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED).isBefore(LocalDate.now());
    }


}
