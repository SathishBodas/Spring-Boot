package com.example.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.student.Model.Courses;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Integer> {
}
