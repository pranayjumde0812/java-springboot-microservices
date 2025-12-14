package com.application.jobapp.job.service;

import com.application.jobapp.job.model.Job;

import java.util.List;

public interface JobService {

    void createJob(Job job);

    List<Job> getAllJobs();

    Job getJobById(int id);
}
