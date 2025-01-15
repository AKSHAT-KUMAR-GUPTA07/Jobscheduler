package com.capstone.Jobscheduler.configurations;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capstone.Jobscheduler.scheduler.ScheduledTask;

@Configuration
public class QuartzConfig {
 // Step 1: Define the Job (the task to execute)
    // @Bean
    // public JobDetail jobDetail(){
    //     return JobBuilder.newJob(ScheduledJob.class) // Tells Quartz to use ScheduledJob for this task
    //     .withIdentity("scheduledJob") // Assigns an identifier to the job
    //     .storeDurably().build();  // Makes the job persistent (not deleted after execution)
    // }

    // // Step 2: Define the Trigger (when to execute the job)

    // @Bean
    // public Trigger jobTrigger(JobDetail jobDetail){
    //     return TriggerBuilder.newTrigger()
    //     .forJob(jobDetail)
    //     .withIdentity("scheduledJobTrigger")
    //     .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
    // }

}
