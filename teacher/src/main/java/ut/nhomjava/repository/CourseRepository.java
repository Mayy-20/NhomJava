package ut.nhomjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.nhomjava.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // ID của Course (trước đây là KhoaHoc) đã đổi từ Integer sang Long
}