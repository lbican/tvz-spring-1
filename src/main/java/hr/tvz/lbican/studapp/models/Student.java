package hr.tvz.lbican.studapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String jmbag;
    private Integer numberOfECTS;
}
