package com.example.jobservice.job;

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
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<Job>> findAll() {
        List<Job> job = serviceImpl.findAll();
        if(job != null) return ResponseEntity.ok(serviceImpl.findAll());
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.GET)
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = serviceImpl.getJobById(id);
        if(job != null) return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(new Job(1L,"TestJob","test job", 100,200,"IND"), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.POST)
    public ResponseEntity<String> SaveJob(@RequestBody Job job) {
        serviceImpl.saveJob(job);
        return new ResponseEntity<>("Job added", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String> UpdateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean updated = serviceImpl.updateJobById(id,updatedJob);
        if(updated) return new ResponseEntity<>("updated", HttpStatus.OK);
        return new ResponseEntity<>("Not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> DeleteJob(@PathVariable Long id) {
        serviceImpl.deleteJobById(id);
        return new ResponseEntity<>("Job deleted", HttpStatus.OK);

    }
}
