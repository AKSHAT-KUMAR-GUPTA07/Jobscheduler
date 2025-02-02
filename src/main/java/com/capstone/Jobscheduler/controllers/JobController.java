package com.capstone.Jobscheduler.controllers;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.Jobscheduler.entities.ScheduledJob;
import com.capstone.Jobscheduler.services.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    //schedule jobs
    @PostMapping("/schedule")
    public String scheduleJob(@RequestBody ScheduledJob job){
        try{
            jobService.scheduleJob(job);
            return "job scheduled successfully!";
        }catch (SchedulerException e){
            return "Error scheduling job: "+e.getMessage();
        }
    }

    //listing all jobs
    @GetMapping
    public List<ScheduledJob> getAllJobs(){
        return jobService.getAllJobs();
    }

    //deleting job
    @DeleteMapping("/delete")
    @Transactional
    public String deleteJob(@RequestParam String jobName , @RequestParam String jobGroup){
        try{
            jobService.deleteJob(jobName, jobGroup);
            return "job deleted successfully!";
        }catch (SchedulerException e){
            return "Error deleting job: "+e.getMessage();
        }
    }

    //updating job
    @PutMapping("/update")
    public ResponseEntity<String>  updateJobSchedule(@RequestBody ScheduledJob job){
        try{
            jobService.updateJobSchedule(job);
            return ResponseEntity.ok("job updated successfully!");
        }catch(SchedulerException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating job schedule: "+e.getMessage());
        }
    }
}
