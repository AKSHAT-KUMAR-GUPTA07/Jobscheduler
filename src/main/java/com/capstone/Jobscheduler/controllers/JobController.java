package com.capstone.Jobscheduler.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.Jobscheduler.entities.Job;
import com.capstone.Jobscheduler.repositories.JobRepository;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    private List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    @PostMapping
    private Job createJob(@RequestBody Job job){
        return jobRepository.save(job);
    }

}
