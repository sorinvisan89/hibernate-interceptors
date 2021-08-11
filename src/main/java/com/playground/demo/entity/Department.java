package com.playground.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;

    @Column(name = "name")
    private String departmentName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parentDepartment")
    @JsonBackReference
    @ToString.Exclude
    List<Employee> employeeList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Department that = (Department) o;

        return Objects.equals(departmentId, that.departmentId);
    }

    @Override
    public int hashCode() {
        return 1487346027;
    }
}
