package com.capstone.Jobscheduler.controllers;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/airflow")
public class AirflowController {

    private final String AIRFLOW_API_URL = "http://localhost:8085/api/v1"; // Airflow API URL
    private final String AIRFLOW_USERNAME = "admin"; // Airflow username
    private final String AIRFLOW_PASSWORD = "Password"; // Airflow password

    // Helper method to create headers with Basic Authentication
    private HttpEntity<String> createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(AIRFLOW_USERNAME, AIRFLOW_PASSWORD); // Set Basic Auth header
        return new HttpEntity<>(headers);
    }

    // Get all DAGs from Airflow
    @GetMapping("/dags")
    public ResponseEntity<String> getAllDags() {
        RestTemplate restTemplate = new RestTemplate();
        String dagsUrl = AIRFLOW_API_URL + "/dags"; // Endpoint to fetch DAGs

        try {
            // Send GET request to Airflow API
            ResponseEntity<String> response = restTemplate.exchange(
                    dagsUrl,
                    HttpMethod.GET,
                    createAuthHeaders(),
                    String.class);

            // Return the response from Airflow API
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            // Handle 401 Unauthorized or other client errors
            return ResponseEntity.status(e.getStatusCode())
                    .body("Error fetching DAGs: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            // Handle other exceptions (e.g., connection issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> triggerDag(String dagId) {
        RestTemplate restTemplate = new RestTemplate();
        String triggerUrl = AIRFLOW_API_URL + "/dags/" + dagId + "/dagRuns"; // Endpoint to trigger a DAG

        try {
            // Use an empty JSON payload
            Map<String, Object> requestBody = new HashMap<>();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, createAuthHeaders().getHeaders());

            // Send POST request to Airflow API
            ResponseEntity<String> response = restTemplate.exchange(
                    triggerUrl,
                    HttpMethod.POST,
                    request,
                    String.class);

            // Return the response from Airflow API
            return ResponseEntity.ok("DAG " + dagId + " triggered successfully!");
        } catch (HttpClientErrorException e) {
            // Handle 404 Not Found or other client errors
            return ResponseEntity.status(e.getStatusCode())
                    .body("Error triggering DAG: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            // Handle other exceptions (e.g., connection issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-example-dag")
    public ResponseEntity<String> triggerExampleDag() {
        String dagId = "example_bash_operator"; // Example DAG ID
        return triggerDag(dagId);
    }
}