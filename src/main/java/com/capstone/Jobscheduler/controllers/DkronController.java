package com.capstone.Jobscheduler.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.Jobscheduler.dto.DkronJobRequest;
import com.capstone.Jobscheduler.services.DkronService;

@RestController //RestController to communicate with postman client ,,, controller to connect it to frontend
@RequestMapping("/dkron")
public class DkronController {

    @Autowired
    private DkronService dkronService;

    @PostMapping("/create")
    public String createJob(@RequestBody DkronJobRequest jobRequest){
        return dkronService.createJob(jobRequest);
    }

    @GetMapping("/list")
    public String listJobs(){
        return dkronService.listJobs();
    }

    @DeleteMapping("/delete/{jobName}")
    public String deleteJob(@PathVariable String jobName){
        return dkronService.deleteJob(jobName);
    }
}
