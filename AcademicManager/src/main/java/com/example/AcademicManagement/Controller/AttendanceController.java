package com.example.AcademicManagement.Controller;

import com.example.AcademicManagement.Entity.AttendanceRecord;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.AcademicManagement.Service.AttendanceService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder; // Import UriComponentsBuilder

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.gateway.url}")
    private String apiGatewayUrl;


    private Integer getStudentIdFromRequest(HttpServletRequest request) {
        return (Integer) request.getAttribute("userId");
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<?> getAttendanceBySemester(
            HttpServletRequest request,
            @PathVariable("semester") Integer semester) {

        Integer studentId = getStudentIdFromRequest(request);
        List<AttendanceRecord> attendanceRecords = attendanceService.getAttendanceByStudentAndSemester(studentId, semester);

        if (attendanceRecords.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No attendance records found for student in semester " + semester);
        }

        return ResponseEntity.ok(attendanceRecords);
    }

    @GetMapping("/course/{courseCode}")
    public ResponseEntity<?> getAttendanceByCourse(
            HttpServletRequest request,
            @PathVariable("courseCode") String courseCode) {

        Integer studentId = getStudentIdFromRequest(request);
        List<AttendanceRecord> attendanceRecords = attendanceService.getAttendanceByStudentAndCourse(studentId, courseCode);

        if (attendanceRecords.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No attendance records found for course " + courseCode);
        }

        return ResponseEntity.ok(attendanceRecords);
    }

    @PostMapping("/triggerNotification")
    public ResponseEntity<String> triggerNotification(HttpServletRequest request) {
        Integer studentId = getStudentIdFromRequest(request);
        String message = "This is a test notification from Academic Manager, triggered by student ID: " + studentId;

        String url = UriComponentsBuilder.fromHttpUrl(apiGatewayUrl + "/notifications/send")
                .queryParam("userId", Long.valueOf(studentId))
                .queryParam("message", message)
                .toUriString();

        try {
            String response = restTemplate.postForObject(url, null, String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error sending notification: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}