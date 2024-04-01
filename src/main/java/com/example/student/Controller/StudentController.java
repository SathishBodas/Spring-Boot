package com.example.student.Controller;

//import org.springframework.stereotype.Repository;
import com.example.student.Service.StudentServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.student.Model.Student;

import java.util.*;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImple serImp;
    @GetMapping("/getAll")
    public ArrayList<Student> getAll()
    {

        return serImp.getAllStudents();
    }
    @PostMapping("/addStudent")
     public Student addStudent(@RequestBody Student student)
    {
        return serImp.addStudent(student);
    }
    @PutMapping("/updateStudent/{id}")
    public Student updateStudent(@PathVariable("id") int id,@RequestBody Student student)
    {
        return serImp.updateStudent(id,student);
    }
    @GetMapping("/search/{id}")
    public Student searchById(@PathVariable("id") int id)
    {
        return serImp.getStudentById(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id)
    {

        return serImp.deleteStudentById(id);
    }
    @GetMapping("/student/age")
    public List<Student> getStudentAgeBetween(@RequestParam("minAge") int minAge,@RequestParam("maxAge") int maxAge)
    {
        return serImp.getStudentAgeBetween(minAge,maxAge);
    }
}
