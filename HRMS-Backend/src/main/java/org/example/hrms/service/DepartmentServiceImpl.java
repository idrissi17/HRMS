package org.example.hrms.service;

import jakarta.transaction.Transactional;
import org.example.hrms.dao.entities.Department;
import org.example.hrms.dao.entities.Employee;
import org.example.hrms.dao.repository.DepartmentRepository;
import org.example.hrms.dao.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

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
    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }

    @Override
    public Employee getDepartmentManager(Long departmentId) {
        Department department =departmentRepository.findById(departmentId).orElseThrow(
                ()->new RuntimeException("Department not found")
        );
        return department.getManager();
    }

    @Override
    public void assignDepartmentManager(Long departmentId, Long employeeId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if(department.getManager()!= null){
            throw new RuntimeException("Department has already manager "+department.getManager().getManagerCode());
        }
        if (!employee.isManager()) {
            throw new RuntimeException("Employee is not a manager");
        }
        department.setManager(employee);
        updateDepartment(department);
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
