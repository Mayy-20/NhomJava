package com.hannah.demo.controller;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.dto.KhoaHocDTO;
import com.hannah.demo.service.KhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin-courses")
@CrossOrigin(origins = "*")
public class KhoaHocController {
    
    @Autowired
    private KhoaHocService khoaHocService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<KhoaHocDTO>>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer instructor) {
        
        ApiResponse<Page<KhoaHocDTO>> response = khoaHocService.getAllCourses(page, size, search, status, instructor);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KhoaHocDTO>> getCourseById(@PathVariable Integer id) {
        ApiResponse<KhoaHocDTO> response = khoaHocService.getCourseById(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<String>> approveCourse(@PathVariable Integer id) {
        ApiResponse<String> response = khoaHocService.approveCourse(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<String>> rejectCourse(@PathVariable Integer id, 
                                                           @RequestBody Map<String, String> request) {
        String reason = request.get("reason");
        ApiResponse<String> response = khoaHocService.rejectCourse(id, reason);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Integer id) {
        ApiResponse<String> response = khoaHocService.deleteCourse(id);
        return ResponseEntity.ok(response);
    }
}
