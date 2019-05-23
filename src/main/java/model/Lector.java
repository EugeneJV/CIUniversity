package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Lector implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int salary;

    @Enumerated(EnumType.STRING)
    private DegreeOfLector degree;

    @ManyToMany(mappedBy = "lectors")
    private Set<Department> departments = new HashSet<>();

    public Lector(Lector lec) {
    }

    public Lector(String name, int salary, DegreeOfLector degree) {
        this.name = name;
        this.salary = salary;
        this.degree = degree;
    }

    public Lector(String name, int salary, DegreeOfLector degree, Set<Department> departments) {
        this.name = name;
        this.salary = salary;
        this.degree = degree;
        this.departments = departments;
    }

    public Lector() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public DegreeOfLector getDegree() {
        return degree;
    }

    public void setDegree(DegreeOfLector degree) {
        this.degree = degree;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", degree=" + degree +
                '}';
    }
}
