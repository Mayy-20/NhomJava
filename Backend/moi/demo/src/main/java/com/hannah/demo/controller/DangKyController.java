package com.hannah.demo.controller;

import com.hannah.demo.model.DangKy;
import com.hannah.demo.service.DangKyService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dangky")
public class DangKyController {
    @Autowired
    private DangKyService dangKyService;

    @GetMapping
    public List<DangKy> getAllDangKy() {
        return dangKyService.getAllDangKy();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DangKy> getDangKyById(@PathVariable int id) {
        Optional<DangKy> dangKy = dangKyService.getDangKyById(id);
        return dangKy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DangKy createDangKy(@RequestBody DangKy dangKy) {
        return dangKyService.createDangKy(dangKy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DangKy> updateDangKy(@PathVariable int id, @RequestBody DangKy dangKyDetails) {
        try {
            DangKy updatedDangKy = dangKyService.updateDangKy(id, dangKyDetails);
            return ResponseEntity.ok(updatedDangKy);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDangKy(@PathVariable int id) {
        try {
            dangKyService.deleteDangKy(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}