package com.example.student.Service;

import com.example.student.Model.Courses;
import com.example.student.Model.Student;
import com.example.student.Repository.CoursesRepository;
import com.example.student.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CoursesServiceImple implements CoursesService{
    @Autowired
    private CoursesRepository courseRepo;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Courses> findAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Courses addCourse(Courses course) {
        return courseRepo.save(course);
    }

    @Override
    public Courses updateCourse(Integer courseId, Courses course) {
        try{
            Courses c=courseRepo.findById(courseId).get();
            if(course.getCourseName()!=null)
            {
                c.setCourseName(course.getCourseName());
            }
            if(course.getCourseFee()!=0)
            {
                c.setCourseFee(course.getCourseFee());
            }
            return courseRepo.save(c);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Courses findCourseById(Integer courseId) {

            Courses c=courseRepo.findById(courseId).orElseThrow(()-> new RuntimeException("Provide correct Id"));
            return c;
    }

    @Override
    public void deleteCourse(Integer courseId) {
        try{
            Courses course=courseRepo.findById(courseId).get();
            List<Student> students=course.getStudents();
            for(Student s:students)
            {
                s.getCourses().remove(course);
            }
           studentRepository.saveAll(students);
            courseRepo.deleteById(courseId);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Provide Correct Id");
        }
    }
}
