package org.example.hrms.dao.repository;

import org.example.hrms.dao.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation,Long> {

    List<Vacation> findByEmployeeEmployeeId(Long employeeId);

    List<Vacation> findByEmployeeEmployeeIdAndEndDateBefore(Long employeeId, Date today);
}
