package com.application.jobapp.job.service.impl;

import com.application.jobapp.job.model.Job;
import com.application.jobapp.job.service.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private static final List<Job> jobs = new ArrayList<>();
    private static Long nextId = 1L;

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobs;
    }

    @Override
    public Job getJobById(int id) {
        Job job = jobs.stream()
                .filter(j -> j.getId() == id)
                .findFirst()
                .get();
        return job;
    }
}
