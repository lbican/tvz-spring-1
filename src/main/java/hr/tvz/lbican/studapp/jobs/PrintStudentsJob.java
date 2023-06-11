package hr.tvz.lbican.studapp.jobs;

import hr.tvz.lbican.studapp.student.StudentDTO;
import hr.tvz.lbican.studapp.student.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PrintStudentsJob implements Job {

    private final StudentService studentService;

    @Autowired
    public PrintStudentsJob(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<StudentDTO> students = studentService.findStudents();

        log.info("Currently enrolled students:");
        log.info("============================");
        students.forEach(student -> log.info(student.printDetails()));
        log.info("============================");
    }
}
