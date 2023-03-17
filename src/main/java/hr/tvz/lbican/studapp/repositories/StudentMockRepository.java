package hr.tvz.lbican.studapp.repositories;

import hr.tvz.lbican.studapp.models.Student;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentMockRepository implements StudentRepository{

    private List<Student> studentList;

    public StudentMockRepository(){
        studentList = new ArrayList<>();
        studentList.add(new Student("Luka", "Bićan", LocalDate.now().minusYears(21), "0246096016", 154));
        studentList.add(new Student("Petar", "Bićan", LocalDate.now().minusYears(20), "0246096017", 36));
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public Optional<Student> findStudentByJmbag(String jmbag) {
        return studentList.stream().filter((student) -> student.getJmbag().equals(jmbag)).findAny();
    }
}
