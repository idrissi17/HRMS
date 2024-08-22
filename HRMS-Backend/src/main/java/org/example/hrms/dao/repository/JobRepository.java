package org.example.hrms.dao.repository;

import org.example.hrms.dao.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job>findByDepartmentDepartmentId(Long departmentId);
    List<Job>findByJobTitleContainingIgnoreCase(String JobTitle);
}
