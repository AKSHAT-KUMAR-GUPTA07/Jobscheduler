package com.capstone.Jobscheduler.controllers;

import java.util.List;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.capstone.Jobscheduler.entities.ScheduledJob;
import com.capstone.Jobscheduler.services.JobService;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Home page - List all jobs
    @GetMapping
    public String getAllJobs(Model model) {
        List<ScheduledJob> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "jobs/list"; 
    }

    // Schedule a new job
    @GetMapping("/schedule")
    public String showScheduleJobForm(Model model) {
        model.addAttribute("job", new ScheduledJob());
        return "jobs/schedule"; 
    }

    @PostMapping("/schedule")
    public String scheduleJob(@ModelAttribute ScheduledJob job, Model model) {
        try {
            jobService.scheduleJob(job);
            model.addAttribute("message", "Job scheduled successfully!");
        } catch (SchedulerException e) {
            model.addAttribute("error", "Error scheduling job: " + e.getMessage());
        }
        return "redirect:/jobs"; 
    }

    // Delete a job
    @PostMapping("/delete")
    public String deleteJob(@RequestParam String jobName, @RequestParam String jobGroup, Model model) {
        try {
            jobService.deleteJob(jobName, jobGroup);
            model.addAttribute("message", "Job deleted successfully!");
        } catch (SchedulerException e) {
            model.addAttribute("error", "Error deleting job: " + e.getMessage());
        }
        return "redirect:/jobs"; 
    }

    // Pause a job
    @PostMapping("/pause")
    public String pauseJob(@RequestParam String jobName, @RequestParam String jobGroup, Model model) {
        try {
            jobService.pauseJob(jobName, jobGroup);
            model.addAttribute("message", "Job paused successfully!");
        } catch (SchedulerException e) {
            model.addAttribute("error", "Error pausing job: " + e.getMessage());
        }
        return "redirect:/jobs"; 
    }

    // Resume a job
    @PostMapping("/resume")
    public String resumeJob(@RequestParam String jobName, @RequestParam String jobGroup, Model model) {
        try {
            jobService.resumeJob(jobName, jobGroup);
            model.addAttribute("message", "Job resumed successfully!");
        } catch (SchedulerException e) {
            model.addAttribute("error", "Error resuming job: " + e.getMessage());
        }
        return "redirect:/jobs"; 
    }
}