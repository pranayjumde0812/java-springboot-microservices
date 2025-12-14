package com.application.jobapp.job.controller;

import com.application.jobapp.job.model.Job;
import com.application.jobapp.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping()
    public String createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return "Job created successfully";
    }

    @GetMapping("/jobs")
    public List<Job> findAllJob() {

        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job findJobById(@PathVariable int id) {
        return jobService.getJobById(id);
    }

}
