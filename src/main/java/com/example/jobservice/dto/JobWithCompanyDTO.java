package com.example.jobservice.dto;

import com.example.jobservice.external.Company;
import com.example.jobservice.job.Job;

public class JobWithCompanyDTO {
    Company company;
    Job job;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
