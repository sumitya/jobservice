package com.example.jobservice.job;

import com.example.jobservice.dto.JobWithCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobServiceImpl serviceImpl;

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        List<JobWithCompanyDTO> job = serviceImpl.findAll();
        if (job != null) return ResponseEntity.ok(serviceImpl.findAll());
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = serviceImpl.getJobById(id);
        if (job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(new Job(1L, "TestJob", "test job", 100, 200, "IND", 1L), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> saveJob(@RequestBody Job job) {
        serviceImpl.saveJob(job);
        return new ResponseEntity<>("Job added", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = serviceImpl.updateJobById(id, updatedJob);
        if (updated) return new ResponseEntity<>("updated", HttpStatus.OK);
        return new ResponseEntity<>("Not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        serviceImpl.deleteJobById(id);
        return new ResponseEntity<>("Job deleted", HttpStatus.OK);

    }
}
