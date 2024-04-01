package com.example.student.Controller;

import com.example.student.Model.Courses;
import com.example.student.Service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    @Autowired
    private CoursesService coursesService;
    @GetMapping("/getAll")
    public List<Courses> findAllCourses()
    {
        return coursesService.findAllCourses();
    }
    @PostMapping("/addCourse")
    public Courses addCourse(@RequestBody Courses course)
    {
        return coursesService.addCourse(course);
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<?> getById(@PathVariable("courseId") Integer courseId)
    {
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(coursesService.findCourseById(courseId));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseId") Integer courseId)
    {
        try{
            coursesService.deleteCourse(courseId);
            return  ResponseEntity.status(HttpStatus.OK).build();
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/updateCourse/{courseId}")
    public Courses updateCourse(@PathVariable("courseId") Integer courseId,@RequestBody Courses course)
    {
        return coursesService.updateCourse(courseId,course);
    }
}
