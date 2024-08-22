package org.example.hrms.service;

import org.example.hrms.dao.entities.Employee;
import org.example.hrms.dao.entities.Job;

import java.util.Collection;
import java.util.List;


public interface JobService {

        public Job addJob(Job job);
        public Job updateJob(Job job);
        public Boolean deleteJob(Long jobId);
        public Job getJobById(Long jobId);
        public List<Job>getAllJobs();
        public Collection<Employee> getEmployeesByJobId(Long jobId);
        public List<Job> getJobsByDepartmentId(Long departmentId);
        public List<Job> getJobsByTitle(String title);
        public Boolean assignEmployeeToJob(Long jobId, Long employeeId);
        public Boolean removeEmployeeFromJob(Long jobId, Long employeeId);


}
