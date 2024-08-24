package com.example.jobservice.job;

import com.example.jobservice.dto.JobWithCompanyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    Job getJobById(Long id);

    List<JobWithCompanyDTO> findAll();

    void deleteJobById(Long id);
}
