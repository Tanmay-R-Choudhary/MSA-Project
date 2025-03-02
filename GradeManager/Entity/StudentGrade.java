package GradeManager.GradeManager.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "student_grades")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Integer gradeId;

    @Column(name = "student_sap")
    private Integer studentSap;

    @Column(name = "course")
    private String course;

    @Column(name = "marks_obtained")
    private BigDecimal marksObtained;

    @Column(name = "exam_type")
    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @Column(name = "semester")
    private Integer semester;

    public enum ExamType {
        m1, m2, term_work, tee
    }
}