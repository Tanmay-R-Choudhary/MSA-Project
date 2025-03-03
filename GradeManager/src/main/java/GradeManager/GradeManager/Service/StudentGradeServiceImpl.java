package GradeManager.GradeManager.Service;

import GradeManager.GradeManager.DTO.CourseGradesDTO;
import GradeManager.GradeManager.Entity.StudentGrade;
import GradeManager.GradeManager.Repository.StudentGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentGradeServiceImpl implements StudentGradeService {
    @Autowired
    private StudentGradeRepository studentGradeRepository;

    public List<CourseGradesDTO> getGradesBySemester(Integer studentSap, Integer semester) {
        List<StudentGrade> grades = studentGradeRepository.findByStudentSapAndSemester(studentSap, semester);
        return convertToCourseDTOs(grades);
    }

    public CourseGradesDTO getGradesByCourse(Integer studentSap, Integer semester, String course) {
        List<StudentGrade> grades = studentGradeRepository.findByStudentSapAndSemesterAndCourse(studentSap, semester, course);

        if (grades.isEmpty()) {
            return null;
        }

        return convertToCourseDTOs(grades).get(0);
    }

    private List<CourseGradesDTO> convertToCourseDTOs(List<StudentGrade> grades) {
        Map<String, List<StudentGrade>> courseMap = grades.stream()
                .collect(Collectors.groupingBy(StudentGrade::getCourse));

        List<CourseGradesDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<StudentGrade>> entry : courseMap.entrySet()) {
            CourseGradesDTO dto = new CourseGradesDTO();
            dto.setCourse(entry.getKey());

            for (StudentGrade grade : entry.getValue()) {
                switch (grade.getExamType()) {
                    case m1:
                        dto.setMidterm1(grade.getMarksObtained());
                        break;
                    case m2:
                        dto.setMidterm2(grade.getMarksObtained());
                        break;
                    case term_work:
                        dto.setTermWork(grade.getMarksObtained());
                        break;
                    case tee:
                        dto.setTermEndExam(grade.getMarksObtained());
                        break;
                }
            }

            result.add(dto);
        }

        return result;
    }
}
