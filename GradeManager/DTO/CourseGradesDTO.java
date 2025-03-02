package GradeManager.GradeManager.DTO;

import java.math.BigDecimal;

public class CourseGradesDTO {
    private String course;
    private BigDecimal midterm1;
    private BigDecimal midterm2;
    private BigDecimal termWork;
    private BigDecimal termEndExam;

    // Getters and Setters
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public BigDecimal getMidterm1() {
        return midterm1;
    }

    public void setMidterm1(BigDecimal midterm1) {
        this.midterm1 = midterm1;
    }

    public BigDecimal getMidterm2() {
        return midterm2;
    }

    public void setMidterm2(BigDecimal midterm2) {
        this.midterm2 = midterm2;
    }

    public BigDecimal getTermWork() {
        return termWork;
    }

    public void setTermWork(BigDecimal termWork) {
        this.termWork = termWork;
    }

    public BigDecimal getTermEndExam() {
        return termEndExam;
    }

    public void setTermEndExam(BigDecimal termEndExam) {
        this.termEndExam = termEndExam;
    }
}
