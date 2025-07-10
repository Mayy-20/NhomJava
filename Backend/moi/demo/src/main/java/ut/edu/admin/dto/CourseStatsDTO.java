package ut.edu.admin.dto;

import lombok.Data;

@Data
public class CourseStatsDTO {
    private long totalCourses;
    private long activeCourses;
    private long pendingCourses;
    private long hiddenCourses;
    private long totalStudents;
    private double averageRating;
}
