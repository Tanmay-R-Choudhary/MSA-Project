package com.example.AcademicManagement.Service;

import com.example.AcademicManagement.Entity.AttendanceRecord;

import java.util.List;

public interface AttendanceService {
    List<AttendanceRecord> getAttendanceByStudentAndSemester(Integer studentId, Integer semester);
    List<AttendanceRecord> getAttendanceByStudentAndCourse(Integer studentId, String courseCode);
}