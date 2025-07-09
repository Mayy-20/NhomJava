package com.hannah.demo.controller;

import com.hannah.demo.model.KhoaHoc;
import com.hannah.demo.service.KhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class KhoaHocController {

    @Autowired
    private KhoaHocService khoaHocService;

    @GetMapping
    public List<KhoaHoc> getAllCourses() {
        return khoaHocService.getAllKhoaHoc();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<KhoaHoc> getCourseById(@PathVariable(value = "id") int id) {
         KhoaHoc khoaHoc = khoaHocService.getKhoaHocById(id)
                .orElse(null);
        if(khoaHoc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(khoaHoc);
    }

    @PostMapping
    public KhoaHoc createCourse(@RequestBody KhoaHoc khoaHoc) {
        return khoaHocService.createKhoaHoc(khoaHoc);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<KhoaHoc> updateCourse(@PathVariable(value = "id") int id,
                                                @RequestBody KhoaHoc courseDetails) {
        KhoaHoc updatedCourse = khoaHocService.updateKhoaHoc(id, courseDetails);
        return ResponseEntity.ok(updatedCourse);
    }
}