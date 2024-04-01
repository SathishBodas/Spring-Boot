package com.example.student.Service;

import com.example.student.Model.Courses;

import java.util.ArrayList;
import java.util.List;

public interface CoursesService {
    List<Courses> findAllCourses();
    Courses addCourse(Courses course);
    Courses updateCourse(Integer courseId,Courses course);
    Courses findCourseById(Integer courseId);
    void deleteCourse(Integer courseId);

}
