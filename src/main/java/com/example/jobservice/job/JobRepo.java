package com.example.jobservice.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Job,Long> {

    //List<Job> findAll();
    Optional<Job> getJobById(Long id);
    //boolean deleteJobById(Long id);
}
