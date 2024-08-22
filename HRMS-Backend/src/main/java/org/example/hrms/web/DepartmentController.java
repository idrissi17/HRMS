package org.example.hrms.web;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.hrms.dao.entities.Department;
import org.example.hrms.dao.entities.Employee;
import org.example.hrms.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> allDepartments(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(departmentService.getAllDepartments());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id){
        Department department =departmentService.getDepartmentById(id);
        if(department==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
            return  ResponseEntity.ok(department);
    }

    @PostMapping("/saveDepartment")
    public ResponseEntity<Department>addDepartment(@Valid @RequestBody Department department){
        Department departmentCreated=departmentService.addDepartment(department);
        log.info("Department is saved : "+departmentCreated.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentCreated);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<String>deleteDepartment(@PathVariable Long id){
        Boolean isRemoved=departmentService.deleteDepartment(id);
        if(!isRemoved){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found with ID : " + id);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Department deleted successfuly");
    }
    @DeleteMapping("/{departmentId}/removeEmployee/{employeeCode}")
    public ResponseEntity<String>removeEmployeeFromDepartment(@PathVariable Long departmentId,@PathVariable String employeeCode){
        try {
            boolean isRemoved = departmentService.removeEmployeeFromDepartment(departmentId,employeeCode);
            if (isRemoved) {
                return ResponseEntity.ok("Employee removed from department successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Employee not found in the specified department.");
            }
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable("name") String departmentName) {
        Department department = departmentService.getDepartmentByName(departmentName);
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{departmentId}/manager")
    public ResponseEntity<Employee> getDepartmentManager(@PathVariable("departmentId") Long departmentId) {
        try {
            Employee manager = departmentService.getDepartmentManager(departmentId);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{departmentId}/assignManager/{employeeId}")
    public ResponseEntity<Void> assignDepartmentManager(@PathVariable("departmentId") Long departmentId,
                                                        @PathVariable("employeeId") Long employeeId) {
        try {
            departmentService.assignDepartmentManager(departmentId, employeeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
