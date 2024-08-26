package org.example.hrms.service;

import org.example.hrms.dao.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public Boolean deleteEmployee(long employeeId);
    public List<Employee>getAllEmployees();
    public Employee getEmployeeById(long employeeId);
    public Employee getEmployeeByCode(String employeeCode);
    public Collection<Employee> getEmployeesByDepartmentId(long departmentId);
    public Employee addEmployeeToDepartment(Long departmentId, Employee employee);
    public Employee promoteToManager(Long employeeId);
    public List<Employee> getAllManagers();

    public Page<Employee>getAllEmployees(int pageNumber,int pageSize);

    Page<Employee> searchByFirstNameOrEmployeeCode(String firstName, String employeeCode, Pageable pageable);

}
