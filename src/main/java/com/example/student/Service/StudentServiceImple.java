package com.example.student.Service;

import com.example.student.Model.*;
import com.example.student.Repository.CoursesRepository;
import com.example.student.Repository.StudentRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudentServiceImple implements StudentService {
    @Autowired
    private StudentRepository stuRepo;
    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public ArrayList<Student> getAllStudents() {
        List<Student> all=stuRepo.findAll();
        ArrayList<Student> ans=new ArrayList<>(all);
        return ans;
    }

    @Override
    public Student getStudentById(int id) {
        try {
            return stuRepo.findById(id).get();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Student updateStudent(int id, Student student) {
        try{
            Student s=stuRepo.findById(id).get();
            if(student.getStudentName()!=null)
            {
                s.setStudentName(student.getStudentName());
            }
            if(student.getStandard()!=null)
            {
                s.setStandard(student.getStandard());
            }
            if(student.getAge()!=0)
            {
                s.setAge(student.getAge());
            }
            if(!student.getCourses().isEmpty())
            {
                List<Integer> courseId=new ArrayList<>();
                for(Courses course:student.getCourses())
                {
                   courseId.add(course.getCourseId());
                } 
                for(Courses course:s.getCourses())
                {
                    courseId.add(course.getCourseId());
                }

                List<Courses> completeCourses=coursesRepository.findAllById(courseId);
                s.setCourses(completeCourses);
            }
           return stuRepo.save(s);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Student addStudent(Student student) {
        List<Integer> courseIds=new ArrayList<>();
        for(Courses course:student.getCourses())
        {
            courseIds.add(course.getCourseId());
        }
        try{
            List<Courses> completeCourses=coursesRepository.findAllById(courseIds);
            if(courseIds.size()!=completeCourses.size())
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Provide Correct course Id");
            }
            student.setCourses(completeCourses);
            return stuRepo.save(student);
        }catch (NoSuchElementException e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Provide Correct details");
        }

    }

    @Override
    public ResponseEntity<?> deleteStudentById(int id) {
            try {
                    Student s = stuRepo.findById(id).get();
                        stuRepo.deleteById(id);
                   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            catch(Exception e)
            {

                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }



    }

    @Override
    public List<Student> getStudentAgeBetween(int minAge, int maxAge) {
        List<Student> students=stuRepo.findAll();
        List<Student> ans=students.stream()
                            .filter(n -> (n.getAge()>=minAge && n.getAge()<=maxAge))
                            .collect(Collectors.toList());
        return ans;
     }
}
