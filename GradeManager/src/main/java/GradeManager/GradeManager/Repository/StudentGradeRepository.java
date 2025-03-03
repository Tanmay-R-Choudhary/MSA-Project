package GradeManager.GradeManager.Repository;

import GradeManager.GradeManager.Entity.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentGradeRepository extends JpaRepository<StudentGrade, Integer> {

    List<StudentGrade> findByStudentSapAndSemester(Integer studentSap, Integer semester);

    List<StudentGrade> findByStudentSapAndSemesterAndCourse(Integer studentSap, Integer semester, String course);
}
