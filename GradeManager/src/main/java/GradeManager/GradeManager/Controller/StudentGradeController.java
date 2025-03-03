package GradeManager.GradeManager.Controller;

import GradeManager.GradeManager.DTO.CourseGradesDTO;
import GradeManager.GradeManager.Service.StudentGradeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class StudentGradeController {
    @Autowired
    private StudentGradeService studentGradeService;

    // Helper to get user's SAP ID from user ID in token
    private Integer getSapIdFromRequest(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        // In a real application, you would look up the student's SAP ID using the userId
        // For simplicity, we'll assume userId is the SAP ID
        return userId;
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<?> getGradesBySemester(
            HttpServletRequest request,
            @PathVariable("semester") Integer semester) {

        Integer studentSap = getSapIdFromRequest(request);
        List<CourseGradesDTO> grades = studentGradeService.getGradesBySemester(studentSap, semester);

        if (grades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No grades found for semester " + semester);
        }

        return ResponseEntity.ok(grades);
    }

    @GetMapping("/semester/{semester}/course/{course}")
    public ResponseEntity<?> getGradesByCourse(
            HttpServletRequest request,
            @PathVariable("semester") Integer semester,
            @PathVariable("course") String course) {

        Integer studentSap = getSapIdFromRequest(request);
        CourseGradesDTO grades = studentGradeService.getGradesByCourse(studentSap, semester, course);

        if (grades == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No grades found for course " + course + " in semester " + semester);
        }

        return ResponseEntity.ok(grades);
    }
}
