package com.capstone.Jobscheduler.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capstone.Jobscheduler.dto.DkronJobRequest;
import com.capstone.Jobscheduler.services.DkronService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller // RestController to communicate with postman client ,,, controller to connect
            // it to frontend
@RequestMapping("/dkron")
public class DkronController {

    @Autowired
    private DkronService dkronService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("jobRequest", new DkronJobRequest());
        return "create-job";
    }

    @PostMapping("/create")
    public String createJob(
            @RequestParam String name,
            @RequestParam String schedule,
            @RequestParam String executor,
            @RequestParam String executorConfigJson,
            @RequestParam(required = false) String owner,
            @RequestParam(required = false, defaultValue = "false") boolean disabled,
            RedirectAttributes redirectAttributes) {

        try {
            // Parse JSON config
            Map<String, Object> executorConfig = objectMapper.readValue(
                    executorConfigJson,
                    new TypeReference<Map<String, Object>>() {
                    });

            // Build request object
            DkronJobRequest jobRequest = new DkronJobRequest(
                    name,
                    schedule,
                    executor,
                    executorConfig,
                    owner,
                    disabled);

            String result = dkronService.createJob(jobRequest);
            redirectAttributes.addFlashAttribute("message", result);
        } catch (JsonProcessingException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid JSON format in executor config");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating job: " + e.getMessage());
        }
        return "redirect:/dkron/list";
    }

    @GetMapping("/list")
    public String listJobs(Model model) {
        try {
            String jsonResponse = dkronService.listJobs();
            List<Map<String, Object>> jobs = objectMapper.readValue(
                    jsonResponse,
                    new TypeReference<List<Map<String, Object>>>() {
                    });
            model.addAttribute("jobs", jobs);
        } catch (JsonProcessingException e) {
            model.addAttribute("error", "Error parsing jobs list");
        }
        return "job-list";
    }

    @PostMapping("/delete/{name}")
    public String deleteJob(@PathVariable String name, RedirectAttributes redirectAttributes) {
        try {
            String result = dkronService.deleteJob(name);
            redirectAttributes.addFlashAttribute("message", result);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting job: " + e.getMessage());
        }
        return "redirect:/dkron/list";
    }

}
