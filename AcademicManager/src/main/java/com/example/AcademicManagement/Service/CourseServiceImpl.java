package com.example.AcademicManagement.Service;

import com.example.AcademicManagement.DTO.CourseDTO;
import com.example.AcademicManagement.Entity.Course;
import com.example.AcademicManagement.Repository.CourseRepository;
import com.example.AcademicManagement.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseCode(courseDTO.getCourseCode());

        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

}
