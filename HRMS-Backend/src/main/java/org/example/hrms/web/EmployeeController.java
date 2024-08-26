package org.example.hrms.web;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.hrms.dao.entities.Employee;
import org.example.hrms.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
   public Employee getEmployee(@PathVariable Long id){
       return employeeService.getEmployeeById(id);
   }

   @PostMapping("/save")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee){

       Employee employeeCreated=employeeService.addEmployee(employee);
       log.info(String.format("Employee is Saved %s:",employee.getEmployeeId()));

      return ResponseEntity.status(HttpStatus.CREATED).header("isSave","true").body(employeeCreated);
   }
   @PutMapping("/update/{id}")
   public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
       if (employeeService.getEmployeeById(id)==null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       newEmployee.setEmployeeId(id);
       Employee updatedEmployee = employeeService.updateEmployee(newEmployee);
       log.info("Update is Ok");
       return ResponseEntity.ok(updatedEmployee);
   }
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteEmployee(@PathVariable Long id){
       Boolean isRemoved=employeeService.deleteEmployee(id);
       if(!isRemoved){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
       }
       return ResponseEntity.status(HttpStatus.OK)
               .body("Employee deleted successfully.");
   }

   @PostMapping("/departments/{departmentId}/{addEmployeeCode}")
    public ResponseEntity<Employee>addEmployeeToDepartment(@PathVariable Long departmentId,@PathVariable String addEmployeeCode){
        try {
            Employee employee =employeeService.getEmployeeByCode(addEmployeeCode);
            if (employee == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            Employee addEmployee=employeeService.addEmployeeToDepartment(departmentId,employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(addEmployee);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
   }
   @GetMapping("/allManagers")
    public ResponseEntity<List<Employee>>getAllManagers(){
        List<Employee>managers=employeeService.getAllManagers();
            return new ResponseEntity<>(managers,HttpStatus.OK);
   }
    @GetMapping("/all")
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Employee> employees = employeeService.getAllEmployees(pageNumber, pageSize);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/all/search")
    public Page<Employee> searchEmployees(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "employeeCode", required = false) String employeeCode,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return employeeService.searchByFirstNameOrEmployeeCode(firstName, employeeCode,pageable);
    }

}
