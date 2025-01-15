package com.capstone.Jobscheduler.services;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.Jobscheduler.entities.ScheduledJob;
import com.capstone.Jobscheduler.repositories.ScheduledJobRepository;
import com.capstone.Jobscheduler.scheduler.ScheduledTask;

@Service
public class JobService {

    @Autowired
    private ScheduledJobRepository scheduledJobRepository;

    @Autowired
    private Scheduler scheduler;

    public void scheduleJob(ScheduledJob job) throws SchedulerException{

        ScheduledJob savedJob = scheduledJobRepository.save(job);

        JobDetail jobDetail = JobBuilder.newJob(ScheduledTask.class)
        .withIdentity(savedJob.getJobName() , savedJob.getJobGroup())
        .build();

        Trigger trigger = TriggerBuilder.newTrigger()
        .withIdentity(savedJob.getJobName() , savedJob.getJobGroup())
        .withSchedule(CronScheduleBuilder.cronSchedule(savedJob.getCronExpression()))
        .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    public List<ScheduledJob> getAllJobs(){
        return scheduledJobRepository.findAll();
    }

    public void deleteJob(String jobName , String jobGroup) throws SchedulerException{
        scheduler.deleteJob(new JobKey(jobName, jobGroup));
        scheduledJobRepository.deleteByJobNameAndJobGroup(jobName , jobGroup);
    }
}
