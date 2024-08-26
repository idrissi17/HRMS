package org.example.hrms.service;

import jakarta.transaction.Transactional;
import org.example.hrms.dao.entities.Department;
import org.example.hrms.dao.entities.Employee;
import org.example.hrms.dao.repository.DepartmentRepository;
import org.example.hrms.dao.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Employee addEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null ;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try{
            return employeeRepository.save(employee);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteEmployee(long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public Employee getEmployeeByCode(String employeeCode) {
       try {
           return employeeRepository.findEmployeeByEmployeeCode(employeeCode);
       }catch (Exception exception){
           throw new RuntimeException("Employee Not Found : " + employeeCode);
       }
    }

    @Override
    public Collection<Employee> getEmployeesByDepartmentId(long departmentId) {
        Department department=departmentRepository.findById(departmentId).orElse(null);
        if(department!=null){
            return department.getEmployees();
        }
        throw new RuntimeException("Department not found with ID: " + departmentId);
    }

    @Override
    public Employee addEmployeeToDepartment(Long departmentId, Employee employee) {
        Department department =departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new RuntimeException("Department not found with ID: " + departmentId);
        }
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee promoteToManager(Long employeeId) {
      Employee employee =getEmployeeById(employeeId);
      if(employee==null && employee.isManager()){
          throw new RuntimeException("Employee not found or Employee already manager");
      }
      employee.setManager(true);
        return updateEmployee(employee);
    }

    @Override
    public List<Employee> getAllManagers() {
        return employeeRepository.findAll().stream()
                .filter(Employee::isManager).collect(Collectors.toList());
    }

    @Override
    public Page<Employee> getAllEmployees(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> searchByFirstNameOrEmployeeCode(String firstName, String employeeCode, Pageable pageable) {
        return employeeRepository.findByFirstNameContainingIgnoreCaseOrEmployeeCodeContainingIgnoreCase(firstName, employeeCode, pageable);
    }
}
