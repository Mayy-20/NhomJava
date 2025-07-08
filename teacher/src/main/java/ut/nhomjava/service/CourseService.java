package ut.nhomjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ut.nhomjava.model.Course;
import ut.nhomjava.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) { // ID đã đổi sang Long
        Optional<Course> course = courseRepository.findById(id);
        return course.orElse(null);
    }

    public void deleteById(Long id) { // ID đã đổi sang Long
        courseRepository.deleteById(id);
    }
}