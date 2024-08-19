package org.example.hrms.dao.entities;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "departments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "departmentId")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(name = "department_name", nullable = false)
    @NotBlank(message = "Department name must not be empty.")
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private Collection<Employee>employees=new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private Collection<Job>jobs=new ArrayList<>();

}
