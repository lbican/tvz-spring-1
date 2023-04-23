package hr.tvz.lbican.studapp.repositories;

import hr.tvz.lbican.studapp.models.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class StudentJdbcRepository implements StudentRepository {

    private static final String SELECT_ALL = "SELECT * FROM student";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public StudentJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("student")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Student> findAll() {
        return jdbc.query(SELECT_ALL, this::mapRowToStudent);
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String jmbag) {
        String query = SELECT_ALL + " WHERE jmbag = ?";
        List<Student> students = jdbc.query(query, new Object[]{jmbag}, this::mapRowToStudent);
        return Optional.of(students.get(0));
    }

    @Override
    public Optional<Student> save(Student student) {
        try{
            student.setId(saveStudentDetails(student));
            return Optional.of(student);
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteStudentByJMBAG(String jmbag) {
        jdbc.update("DELETE FROM student WHERE jmbag = ?", jmbag);
    }

    private Student mapRowToStudent(ResultSet rs, int rowNum) throws SQLException {
        return new Student(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getDate("date_of_birth").toLocalDate(),
                rs.getString("jmbag"),
                rs.getInt("ects_points")
        );
    }

    private long saveStudentDetails(Student student) {
        Map<String, Object> values = new HashMap<>();

        values.put("first_name", student.getFirstName());
        values.put("last_name", student.getLastName());
        values.put("jmbag", student.getJmbag());
        values.put("date_of_birth", student.getDateOfBirth());
        values.put("ects_points", student.getNumberOfECTS());

        return inserter.executeAndReturnKey(values).longValue();
    }
}
