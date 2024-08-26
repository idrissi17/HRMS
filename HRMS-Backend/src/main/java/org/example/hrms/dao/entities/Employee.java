package org.example.hrms.dao.entities;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Employees")
@Data
@Builder
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "employee_code", nullable = false)
    @Size(min = 6, message = "Employee code must be at least 6 characters long.")
    private String employeeCode;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name must not be empty.")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name must not be empty.")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email must not be empty.")
    @Email(message = "Email should be valid.")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",message = "Phone number must be valid.")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @Past(message = "Date of birth must be in the past.")
    private Date dateOfBirth;

    @Column(name = "date_of_hire")
    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "Date of hire must be in the past or today.")
    private Date dateOfHire;

    @Column(name = "salary")
    @DecimalMin(value = "1000.00", inclusive = true, message = "Salary must be at least 1000.00 .")
    @Positive(message = "Salary must be a positive number")
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "is_manager")
    private boolean isManager;

    @Column(name = "manager_code")
    private String managerCode;

    @OneToMany(mappedBy = "employee")
    private Collection<Vacation> vacations = new ArrayList<>();

    public Employee(Long employeeId, String employeeCode, String firstName,
                    String lastName, String email, String address, String phoneNumber,
                    Date dateOfBirth, Date dateOfHire, Double salary,
                    Department department,Job job,boolean isManager,String managerCode,Collection vacations) {
        this.employeeId = employeeId;
        this.employeeCode = "EMP-"+String.valueOf(UUID.fromString(UUID.randomUUID().toString())).substring(0,5).toUpperCase();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHire = dateOfHire;
        this.salary = salary;
        this.department = department;
        this.job=job;
        this.isManager=isManager;
        this.vacations=new ArrayList<>();
        if (isManager) {
            this.managerCode = "MGR-" + this.employeeCode.split("-")[1];
        }


    }

}
