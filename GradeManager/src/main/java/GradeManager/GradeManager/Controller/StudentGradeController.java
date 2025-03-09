package GradeManager.GradeManager.Controller;

import GradeManager.GradeManager.DTO.CourseGradesDTO;
import GradeManager.GradeManager.Service.StudentGradeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class StudentGradeController {
    @Autowired
    private StudentGradeService studentGradeService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.gateway.url}")
    private String apiGatewayUrl;

    private Integer getSapIdFromRequest(HttpServletRequest request) {
        return (Integer) request.getAttribute("userId");
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

    @PostMapping("/triggerNotification")
    public ResponseEntity<String> triggerNotification(HttpServletRequest request) {
        Integer studentId = getSapIdFromRequest(request);
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
