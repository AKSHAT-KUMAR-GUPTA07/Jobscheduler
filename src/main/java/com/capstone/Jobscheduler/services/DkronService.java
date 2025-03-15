package com.capstone.Jobscheduler.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capstone.Jobscheduler.dto.DkronJobRequest;

@Service
public class DkronService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String dkronUrl = "http://localhost:8081/v1/jobs";

    public String createJob(DkronJobRequest jobRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DkronJobRequest> requestEntity = new HttpEntity<>(jobRequest,headers);
        ResponseEntity<String> response = restTemplate.exchange(dkronUrl, HttpMethod.POST,requestEntity,String.class);
        return response.getBody();
    }

    public String listJobs(){
        return restTemplate.getForObject(dkronUrl, String.class);
    }

    public String deleteJob(String jobName){
        ResponseEntity<String> response = restTemplate.exchange(dkronUrl + "/" + jobName, HttpMethod.DELETE , null , String.class);
        return response.getBody();
    }
}
