package ut.edu.admin.controller;

import ut.edu.admin.dto.CourseStatsDTO;
import ut.edu.admin.dto.KhoaHocDTO;
import ut.edu.admin.service.KhoaHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class KhoaHocController {
    private final KhoaHocService khoaHocService;

    @GetMapping
    public ResponseEntity<List<KhoaHocDTO>> getAllCourses() {
        return ResponseEntity.ok(khoaHocService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhoaHocDTO> getCourseById(@PathVariable Integer id) {
        return ResponseEntity.ok(khoaHocService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<KhoaHocDTO> createCourse(@RequestBody KhoaHocDTO dto) {
        return ResponseEntity.ok(khoaHocService.createCourse(dto));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<KhoaHocDTO> approveCourse(@PathVariable Integer id) {
        return ResponseEntity.ok(khoaHocService.approveCourse(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<KhoaHocDTO> rejectCourse(@PathVariable Integer id) {
        return ResponseEntity.ok(khoaHocService.rejectCourse(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        khoaHocService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pending")
    public ResponseEntity<List<KhoaHocDTO>> getPendingCourses(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(khoaHocService.getPendingCourses(limit));
    }

    @GetMapping("/stats")
    public ResponseEntity<CourseStatsDTO> getCourseStats() {
        return ResponseEntity.ok(khoaHocService.getCourseStats());
    }
}
