package org.example.hrms.dao.repository;


import org.example.hrms.dao.entities.Department;
import org.example.hrms.dao.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findEmployeeByEmployeeCode(String employeeCode);
    Collection<Employee> findEmployeesByDepartment(Department department);

}
