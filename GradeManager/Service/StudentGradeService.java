package GradeManager.GradeManager.Service;

import GradeManager.GradeManager.DTO.CourseGradesDTO;
import GradeManager.GradeManager.Entity.StudentGrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface StudentGradeService {
    public List<CourseGradesDTO> getGradesBySemester(Integer studentSap, Integer semester);
    public CourseGradesDTO getGradesByCourse(Integer studentSap, Integer semester, String course);
}
