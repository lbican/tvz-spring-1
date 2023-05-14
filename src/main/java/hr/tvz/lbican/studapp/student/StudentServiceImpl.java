package hr.tvz.lbican.studapp.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    private static final int YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED = 26;

    @Override
    public List<StudentDTO> findStudents() {
        return studentRepository.findAll().stream().map(this::mapStudentToDTO).toList();
    }

    @Override
    public Optional<StudentDTO> findByJMBAG(String jmbag) {
        return studentRepository.findStudentByJMBAG(jmbag).map(this::mapStudentToDTO);
    }

    @Override
    public Optional<StudentDTO> save(StudentCommand studentCommand) {
        return studentRepository.save(mapCommandToStudent(studentCommand)).map(this::mapStudentToDTO);
    }

    @Override
    public Optional<StudentDTO> update(String JMBAG, StudentCommand updatedStudentCommand) {
        return studentRepository.update(JMBAG, mapCommandToStudent(updatedStudentCommand)).map(this::mapStudentToDTO);
    }

    @Override
    public void deleteByJMBAG(String jmbag) {
        studentRepository.deleteStudentByJMBAG(jmbag);
    }

    private StudentDTO mapStudentToDTO(final Student student){
        return new StudentDTO(
                student.getJmbag(),
                student.getFirstName(),
                student.getLastName(),
                student.getNumberOfECTS(),
                this.shouldTuitionBePayed(student.getDateOfBirth())
        );
    }

    private boolean shouldTuitionBePayed(LocalDate dateOfBirth){
        return dateOfBirth.plusYears(YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED).isBefore(LocalDate.now());
    }

    private Student mapCommandToStudent(final StudentCommand studentCommand) {
        return new Student(
                studentCommand.getFirstName(),
                studentCommand.getLastName(),
                studentCommand.getDateOfBirth(),
                studentCommand.getJmbag(),
                studentCommand.getNumberOfECTS()
        );
    }

}
