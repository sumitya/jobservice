package com.example.jobservice.job;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    Job getJobById(Long id);
    List<Job> findAll();
    void deleteJobById(Long id);
}
