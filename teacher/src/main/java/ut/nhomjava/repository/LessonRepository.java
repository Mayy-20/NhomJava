package ut.nhomjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.nhomjava.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    // Thêm các phương thức tùy chỉnh nếu cần, ví dụ:
    // List<Lesson> findByCourse_Id(Long courseId);
}