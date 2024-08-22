package org.example.hrms.service;

import org.example.hrms.dao.entities.Employee;

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

}
