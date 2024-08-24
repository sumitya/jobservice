package com.example.jobservice.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepo repo;

    public Job getJobById(Long id) {
        Optional<Job> jobOptional = repo.getJobById(id);
        if (jobOptional.isPresent()) return jobOptional.get();
        return new Job();
    }

    public List<Job> findAll() {
        return repo.findAll();
    }

    public void deleteJobById(Long id) {
        repo.deleteById(id);
    }

    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = repo.findById(id);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDesc(updatedJob.getDesc());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            repo.save(job);
        }

        return true;
    }

    public void saveJob(Job job) {
        repo.save(job);
    }
}
