package org.example.hrms.service;

import org.example.hrms.dao.entities.Employee;
import org.example.hrms.dao.entities.Job;
import org.example.hrms.dao.repository.EmployeeRepository;
import org.example.hrms.dao.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class JobServiceImplement implements JobService{
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Job addJob(Job job) {
        try {
            return jobRepository.save(job);
        } catch (Exception e) {
            throw new RuntimeException("Error while adding job: " + e.getMessage());
        }
    }

    @Override
    public Job updateJob(Job job) {
        if (!jobRepository.existsById(job.getJobId())) {
            throw new RuntimeException("Job not found with id " + job.getJobId());
        }
        try {
            return jobRepository.save(job);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating job: " + e.getMessage());
        }
    }

    @Override
    public Boolean deleteJob(Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new RuntimeException("Job not found with id " + jobId);
        }
        try {
            jobRepository.deleteById(jobId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting job: " + e.getMessage());
        }
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id " + jobId));
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Collection<Employee> getEmployeesByJobId(Long jobId) {
        Job job=getJobById(jobId);
        return job.getEmployees();
    }
}
