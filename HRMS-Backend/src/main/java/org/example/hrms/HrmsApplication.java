package org.example.hrms;

import org.example.hrms.dao.entities.Department;
import org.example.hrms.dao.entities.Employee;
import org.example.hrms.service.DepartmentService;
import org.example.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HrmsApplication implements CommandLineRunner {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;


	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Department itDepartment = Department.builder()
				.departmentName("IT")
				.build();

		Department marketingDepartment = Department.builder()
				.departmentName("Marketing")
				.build();

		// Save Departments
		departmentService.addDepartment(itDepartment);
		departmentService.addDepartment(marketingDepartment);

		// Create Employees
		Employee employee1 = Employee.builder()
				.firstName("John")
				.lastName("Doe")
				.email("john.doe@example.com")
				.address("123 Main St, Cityville")
				.phoneNumber("+1234567890")
				.dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1990-05-15"))
				.dateOfHire(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-10"))
				.salary(3000.00)
				.department(itDepartment)
				.isManager(true)
				.build();

		Employee employee2 = Employee.builder()
				.firstName("Jane")
				.lastName("Smith")
				.email("jane.smith@example.com")
				.address("456 Elm St, Townsville")
				.phoneNumber("+1987654321")
				.dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1985-10-20"))
				.dateOfHire(new SimpleDateFormat("yyyy-MM-dd").parse("2019-06-15"))
				.salary(4000.00)
				.department(itDepartment)
				.build();

		// More Employees
		Employee employee3 = Employee.builder()
				.firstName("Alice")
				.lastName("Johnson")
				.email("alice.johnson@example.com")
				.address("789 Oak St, Villageburg")
				.phoneNumber("+1123456789")
				.dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1992-03-30"))
				.dateOfHire(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-01"))
				.salary(3500.00)
				.department(itDepartment)
				.build();

		Employee employee4 = Employee.builder()
				.firstName("Michael")
				.lastName("Brown")
				.email("michael.brown@example.com")
				.address("321 Pine St, Hamletville")
				.phoneNumber("+1234567892")
				.dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1980-12-01"))
				.dateOfHire(new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-25"))
				.salary(4500.00)
				.department(itDepartment)
				.build();

		Employee employee5 = Employee.builder()
				.firstName("Emma")
				.lastName("Williams")
				.email("emma.williams@example.com")
				.address("654 Maple St, Capital City")
				.phoneNumber("+1987654322")
				.dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1995-07-19"))
				.dateOfHire(new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-14"))
				.salary(3800.00)
				.department(marketingDepartment)
				.build();

		List<Employee> employeeList = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4, employee5));

		// Save Employees
		employeeList.forEach(employeeService::addEmployee);

		// Update Departments with Employees
		itDepartment.setEmployees(employeeList.subList(0, 4));
		marketingDepartment.setEmployees(employeeList.subList(4, 5));
		departmentService.updateDepartment(itDepartment);
		departmentService.updateDepartment(marketingDepartment);

		// Add a new employee and assign to a department
		Employee newEmployee = Employee.builder()
				.firstName("Ayoub")
				.lastName("Mouchrif")
				.email("mouchrif.ayoub@hotmail.com")
				.address("550 bd mohammed vi, Casablanca")
				.phoneNumber("+1987650322")
				.dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2001-06-20"))
				.dateOfHire(new SimpleDateFormat("yyyy-MM-dd").parse("2025-12-12"))
				.salary(10000.00)
				.build();

		employeeService.addEmployee(newEmployee);
//		itDepartment.setManager(newEmployee);
//		departmentService.assignDepartmentManager(itDepartment.getDepartmentId(),newEmployee.getEmployeeId());
		employeeService.promoteToManager(newEmployee.getEmployeeId());





	}
}
