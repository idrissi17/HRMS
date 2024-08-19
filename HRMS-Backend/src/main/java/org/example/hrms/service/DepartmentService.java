package org.example.hrms.service;

import org.example.hrms.dao.entities.Department;
import org.example.hrms.dao.entities.Employee;

import java.util.List;

public interface DepartmentService {

    public Department addDepartment(Department department);
    public Department updateDepartment(Department department);
    public Boolean deleteDepartment(long departmentId);
    public Department getDepartmentById(Long departmentId);
    public List<Department> getAllDepartments();
    public String getDepartmentName(Long departmentId);
    public Department updateDepartmentName(Long departmentId, String newName);
    public Boolean removeEmployeeFromDepartment(Long departmentId, String employeeCode);

}
