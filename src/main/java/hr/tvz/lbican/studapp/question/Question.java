package hr.tvz.lbican.studapp.question;

import hr.tvz.lbican.studapp.answer.Answer;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answerList;
}
