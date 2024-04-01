package com.example.student.Service;

import java.util.*;
import com.example.student.Model.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ArrayList<Student> getAllStudents();
    Student getStudentById(int id);
    Student updateStudent(int id,Student student);
    Student addStudent(Student student);
   ResponseEntity<?> deleteStudentById(int id);
   List<Student> getStudentAgeBetween(int minAge,int maxAge);
}
