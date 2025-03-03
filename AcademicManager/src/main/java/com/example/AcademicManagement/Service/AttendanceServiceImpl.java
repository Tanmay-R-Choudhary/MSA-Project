package com.example.AcademicManagement.Service;

import com.example.AcademicManagement.Entity.AttendanceRecord;
import com.example.AcademicManagement.Repository.AttendanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Override
    public List<AttendanceRecord> getAttendanceByStudentAndSemester(Integer studentId, Integer semester) {
        return attendanceRecordRepository.findByStudentIdAndSemester(studentId, semester);
    }

    @Override
    public List<AttendanceRecord> getAttendanceByStudentAndCourse(Integer studentId, String courseCode) {
        return attendanceRecordRepository.findByStudentIdAndCourseCode(studentId, courseCode);
    }

}