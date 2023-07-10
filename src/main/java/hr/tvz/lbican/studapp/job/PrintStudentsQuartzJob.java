package hr.tvz.lbican.studapp.job;

import hr.tvz.lbican.studapp.student.StudentDTO;
import hr.tvz.lbican.studapp.student.StudentService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PrintStudentsQuartzJob extends QuartzJobBean {

    private final StudentService studentService;
    public PrintStudentsQuartzJob(StudentService studentService) {
        this.studentService = studentService;
    }

    private String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    protected void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {
        List<StudentDTO> studentList = studentService.findStudents();

        log.info("============");
        studentList.forEach(studentDTO -> log.info(studentDTO.printDetails()));
        log.info("============");
    }
}
