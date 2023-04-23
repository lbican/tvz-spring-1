package hr.tvz.lbican.studapp.repositories;

import hr.tvz.lbican.studapp.models.Student;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StudentMockRepository implements StudentRepository{

    private List<Student> studentList;

    public StudentMockRepository(){
        studentList = new ArrayList<>();
        studentList.add(new Student(1L, "Luka", "Bićan", LocalDate.now().minusYears(21), "0246096016", 154));
        studentList.add(new Student(2L, "Petar", "Bićan", LocalDate.now().minusYears(20), "0246096017", 36));
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String jmbag) {
        return studentList.stream().filter(student -> student.getJmbag().equals(jmbag)).findAny();
    }

    @Override
    public Optional<Student> save(Student student) {
        if(!studentList.contains(student)){
            studentList.add(student);
            return Optional.of(student);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteStudentByJMBAG(String jmbag) {
        studentList.removeIf(it -> Objects.equals(it.getJmbag(), jmbag));
    }
}
