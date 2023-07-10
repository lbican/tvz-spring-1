package hr.tvz.lbican.studapp.config;

import hr.tvz.lbican.studapp.job.PrintCoursesQuartzJob;
import hr.tvz.lbican.studapp.job.PrintStudentsQuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail printStudentsDetail(){
        return JobBuilder.newJob(PrintStudentsQuartzJob.class).withIdentity("printStudents").storeDurably().build();
    }

    @Bean JobDetail printCoursesDetail(){
        return JobBuilder.newJob(PrintCoursesQuartzJob.class).withIdentity("printCourses").storeDurably().build();
    }

    @Bean
    public Trigger printStudentsTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob(printStudentsDetail())
                .withSchedule(scheduleBuilder)
                .withIdentity("printStudentsTrigger").build();
    }

    @Bean
    public Trigger printCoursesJob(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder
                .newTrigger()
                .forJob("printCourses")
                .withSchedule(simpleScheduleBuilder)
                .withIdentity("printCoursesTrigger")
                .build();
    }
}
