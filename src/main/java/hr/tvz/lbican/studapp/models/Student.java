package hr.tvz.lbican.studapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Student {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String jmbag;
    private Integer ectsPoints;
}
