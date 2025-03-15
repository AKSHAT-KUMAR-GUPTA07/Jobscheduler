package com.capstone.Jobscheduler.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capstone.Jobscheduler.dto.DkronJobRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public List<Map<String,Object>> parseJobs(String json) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Map<String , Object>>>() {});
    }

}
