package com.example.jobservice.job;

import com.example.jobservice.dto.JobWithCompanyDTO;
import com.example.jobservice.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepo repo;

    @Autowired
    RestTemplate restTemplate;

    public Job getJobById(Long id) {
        Optional<Job> jobOptional = repo.getJobById(id);
        if (jobOptional.isPresent()) return jobOptional.get();
        return new Job();
    }

    public List<JobWithCompanyDTO> findAll() {
        List<JobWithCompanyDTO> dtos = new ArrayList<>();
        List<Job> jobs = repo.findAll();
        for (Job job : jobs) {
            JobWithCompanyDTO dto = convertToDto(job);
            dtos.add(dto);
        }
        return dtos;
    }

    private JobWithCompanyDTO convertToDto(Job job) {
        JobWithCompanyDTO dto = new JobWithCompanyDTO();
        dto.setJob(job);

        // RestTemplate is useful for interacting with external services/endpoints.
        //RestTemplate restTemplate = new RestTemplate();
        Company company =
                restTemplate.getForObject("http://COMPANYSERVICE:8083/companies/" + job.getCompanyId(),
                        Company.class);
        dto.setCompany(company);
        return dto;
    }

    public void deleteJobById(Long id) {
        repo.deleteById(id);
    }

    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = repo.findById(id);
        if (jobOptional.isPresent()) {
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
