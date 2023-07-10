package hr.tvz.lbican.studapp.job;

import hr.tvz.lbican.studapp.course.CourseDTO;
import hr.tvz.lbican.studapp.course.CourseService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PrintCoursesQuartzJob extends QuartzJobBean {

    private final CourseService courseService;

    public PrintCoursesQuartzJob(CourseService courseService) {
        this.courseService = courseService;
    }

    private String name;

    @Override
    protected void executeInternal(@NonNull JobExecutionContext context) throws JobExecutionException {
        List<CourseDTO> courses = this.courseService.findAll();

        log.info("============");
        courses.forEach((courseDTO -> log.info(courseDTO.printDetails())));
        log.info("============");
    }
}
