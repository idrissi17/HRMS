package org.example.hrms.service;

import org.example.hrms.dao.entities.Department;
import org.example.hrms.dao.entities.Employee;
import org.example.hrms.dao.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DepartmentServiceImplement implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Department addDepartment(Department department) {
        try {
            return departmentRepository.save(department);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add department: " + e.getMessage(), e);
        }
    }

    @Override
    public Department updateDepartment(Department department) {
        try {
            return departmentRepository.save(department);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update department: " + e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteDepartment(long departmentId) {
        if(departmentRepository.existsById(departmentId)){
           Collection<Employee> employees=employeeService.getEmployeesByDepartmentId(departmentId);
           for (Employee employee :employees){
               employee.setDepartment(null);
               employeeService.updateEmployee(employee);
           }
           departmentRepository.deleteById(departmentId);
           return true;
        }
        return false;
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public String getDepartmentName(Long departmentId) {
        Department department = getDepartmentById(departmentId);
        return department.getDepartmentName();
    }

    @Override
    public Department updateDepartmentName(Long departmentId, String newName) {
        Department department = getDepartmentById(departmentId);
        department.setDepartmentName(newName);
        return departmentRepository.save(department);
    }


    @Override
    public Boolean removeEmployeeFromDepartment(Long departmentId, String employeeCode) {
        try {
            Employee employee = employeeService.getEmployeeByCode(employeeCode);
            if (employee.getDepartment() != null && employee.getDepartment().getDepartmentId().equals(departmentId)) {
                employee.setDepartment(null);
                employeeService.updateEmployee(employee);
                return true;
            } else {
                throw new RuntimeException("Employee does not belong to department with id: " + departmentId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove employee from department: " + e.getMessage(), e);
        }

    }
}
