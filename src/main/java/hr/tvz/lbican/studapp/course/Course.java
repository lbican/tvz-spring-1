package hr.tvz.lbican.studapp.course;

import hr.tvz.lbican.studapp.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "ects_points")
    private Integer numberOfECTS;

    @ManyToMany(targetEntity = Student.class, mappedBy = "courses")
    private List<Student> students;
}
