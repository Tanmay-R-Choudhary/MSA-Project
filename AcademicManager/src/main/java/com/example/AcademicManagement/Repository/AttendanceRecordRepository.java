package com.example.AcademicManagement.Repository;

import com.example.AcademicManagement.Entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByStudentIdAndSemester(Integer studentId, Integer semester);
    List<AttendanceRecord> findByStudentIdAndCourseCode(Integer studentId, String courseCode);
}