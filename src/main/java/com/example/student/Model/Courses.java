package com.example.student.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.*;
import com.example.student.Model.Student;

@Entity
@Table(name="courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="courseid")
    private int courseId;
    @Column(name="coursename")
    private String courseName;
    @Column(name="coursefee")
    private int courseFee;
    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    List<Student> students=new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Courses(int courseId, String courseName, int courseFee,List<Student> students) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.students = students;
    }
    public Courses()
    {

    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(int courseFee) {
        this.courseFee = courseFee;
    }
}
