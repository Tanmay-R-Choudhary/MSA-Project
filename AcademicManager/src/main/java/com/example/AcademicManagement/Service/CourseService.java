package com.example.AcademicManagement.Service;

import com.example.AcademicManagement.DTO.CourseDTO;
import com.example.AcademicManagement.Entity.Course;

public interface CourseService {

    Course createCourse(CourseDTO courseDTO);

    Course getCourseById(Long courseId);


}
