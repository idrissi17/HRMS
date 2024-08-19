package org.example.hrms.dao.repository;

import org.example.hrms.dao.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
}
