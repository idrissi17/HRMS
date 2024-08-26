package org.example.hrms.web;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.hrms.dao.entities.Employee;
import org.example.hrms.dao.entities.Job;
import org.example.hrms.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/jobSave")
    public ResponseEntity<Job> addJob(@Valid @RequestBody Job job) {
        Job createdJob = jobService.addJob(job);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job job) {
        if (jobService.getJobById(jobId)==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        job.setJobId(jobId);
        Job updatedJob = jobService.updateJob(job);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
        Job job = jobService.getJobById(jobId);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{jobId}/employees")
    public ResponseEntity<Collection<Employee>> getEmployeesByJobId(@PathVariable Long jobId) {
        try {
            Collection<Employee> employees = jobService.getEmployeesByJobId(jobId);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
