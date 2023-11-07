package com.bn.employeemanagement.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName; // first_name

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "employee_number")
    private Integer employeeNum; // employee_num

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "employee_projects",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Projects> projects;
}
