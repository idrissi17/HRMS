package org.example.hrms.service;

import jakarta.transaction.Transactional;
import org.example.hrms.dao.entities.Employee;
import org.example.hrms.dao.entities.Job;
import org.example.hrms.dao.repository.EmployeeRepository;
import org.example.hrms.dao.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService{
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
        Job job = getJobById(jobId);
        if (job != null) {
            return job.getEmployees();
        }
        return null;

    }

    @Override
    public List<Job> getJobsByDepartmentId(Long departmentId) {
        return jobRepository.findByDepartmentDepartmentId(departmentId);
    }

    @Override
    public List<Job> getJobsByTitle(String JobTitle) {
        return jobRepository.findByJobTitleContainingIgnoreCase(JobTitle);
    }

    @Override
    public Boolean assignEmployeeToJob(Long jobId, Long employeeId) {
        Job job =getJobById(jobId);
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
                ()-> new RuntimeException("Employee not found "));
        if(job!=null && employee!=null){
            job.getEmployees().add(employee);
            updateJob(job);
            return  true;
        }
        return false;

    }

    @Override
    public Boolean removeEmployeeFromJob(Long jobId, Long employeeId) {
        Job job =getJobById(jobId);
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
                ()-> new RuntimeException("Employee not found "));
        if(job !=null && employee!=null){
            job.getEmployees().remove(employee);
            updateJob(job);
            return true;
        }
        return false;
    }
}
