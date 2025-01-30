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
import org.quartz.TriggerKey;
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

    //To schedule job
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

    //to list all jobs
    public List<ScheduledJob> getAllJobs(){
        return scheduledJobRepository.findAll();
    }

    //to delete a job
    public void deleteJob(String jobName , String jobGroup) throws SchedulerException{
        scheduler.deleteJob(new JobKey(jobName, jobGroup));
        scheduledJobRepository.deleteByJobNameAndJobGroup(jobName , jobGroup);
    }

    //to check if the job exists
    // public boolean checkJobExist(String jobName , String jobGroup){
    //     try{
    //         JobKey jobKey = new JobKey(jobName,jobGroup);
    //         return scheduler.checkExists(jobKey);
    //     }catch(SchedulerException e){
    //         e.printStackTrace();
    //         return false;
    //     }
    // } 
    //maybe not needed

    //to update a job
    public void updateJobSchedule(ScheduledJob job)throws SchedulerException{
        JobKey jobKey=new JobKey(job.getJobName(),job.getJobGroup());

        if(!scheduler.checkExists(jobKey)){
            throw new SchedulerException("Job with name "+job.getJobName()+" and group "+job.getJobGroup()+" does not exist");
        }
        //build new trigger with updated cronExpression
        Trigger newTrigger = TriggerBuilder.newTrigger()
        .withIdentity(job.getJobName(),job.getJobGroup())
        .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
        .build();

        //update the quartz schedule
        scheduler.rescheduleJob(new TriggerKey(job.getJobName(), job.getJobGroup()), newTrigger);

        //update in database
        ScheduledJob existingJob = scheduledJobRepository
        .findByJobNameAndJobGroup(job.getJobName(), job.getJobGroup())
        .orElseThrow(()-> new SchedulerException("job not found with name "+job.getJobName()+" and group "+job.getJobGroup()));

        existingJob.setCronExpression(job.getCronExpression());
        scheduledJobRepository.save(existingJob);
    }

    //to trigger a existing job
    public void triggerJob(String jobName , String jobGroup)throws SchedulerException{
        JobKey jobKey = new JobKey(jobName,jobGroup);
        if(!scheduler.checkExists(jobKey)){
            throw new SchedulerException("job with name "+jobName+" and group "+jobGroup+" does not exist");
        }

        //trigger job manually
        scheduler.triggerJob(jobKey);
    }

    //API to pause job
    public void pauseJob(String jobName , String jobGroup)throws SchedulerException{
        JobKey jobKey = new JobKey(jobName, jobGroup);

        if(scheduler.checkExists(jobKey)){
            scheduler.pauseJob(jobKey);
        }else{
            throw new SchedulerException("Job with name "+jobName+" and group "+jobGroup+" not found");
        }
    }
}
