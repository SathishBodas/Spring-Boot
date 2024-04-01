package com.example.student.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.*;
import com.example.student.Model.Courses;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="studentname")
    private String studentName;
    @Column(name="standard")
    private String standard;
    @Column(name="age")
    private int age;
    @ManyToMany
    @JoinTable(
            name="student_courses",
            joinColumns = @JoinColumn(name="stu_id"),
            inverseJoinColumns = @JoinColumn(name="courseid")
    )
    @JsonIgnoreProperties("students")
    private List<Courses> courses=new ArrayList<>();

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Student(int id, String studentName, String standard,int age,List<Courses> courses) {
        this.id = id;
        this.studentName = studentName;
        this.standard = standard;
        this.age=age;
        this.courses=courses;
    }

    public Student() {
    }
}
