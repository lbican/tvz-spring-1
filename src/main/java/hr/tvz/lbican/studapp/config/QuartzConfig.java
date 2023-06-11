package hr.tvz.lbican.studapp.config;

import hr.tvz.lbican.studapp.jobs.PrintStudentsJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail printStudentsJobDetail() {
        return JobBuilder.newJob(PrintStudentsJob.class)
                .withIdentity("PrintStudentsJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger printStudentsJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(printStudentsJobDetail())
                .withIdentity("PrintStudentsTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
