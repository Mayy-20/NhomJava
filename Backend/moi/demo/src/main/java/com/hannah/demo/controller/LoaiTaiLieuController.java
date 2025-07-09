package com.hannah.demo.controller;

import com.hannah.demo.model.LoaiTaiLieu;
import com.hannah.demo.service.LoaiTaiLieuService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loaitailieu")
public class LoaiTaiLieuController {
    @Autowired
    private LoaiTaiLieuService loaiTaiLieuService;

    @GetMapping
    public List<LoaiTaiLieu> getAllLoaiTaiLieu() {
        return loaiTaiLieuService.getAllLoaiTaiLieu();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoaiTaiLieu> getLoaiTaiLieuById(@PathVariable int id) {
        Optional<LoaiTaiLieu> loaiTaiLieu = loaiTaiLieuService.getLoaiTaiLieuById(id);
        return loaiTaiLieu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public LoaiTaiLieu createLoaiTaiLieu(@RequestBody LoaiTaiLieu loaiTaiLieu) {
        return loaiTaiLieuService.createLoaiTaiLieu(loaiTaiLieu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoaiTaiLieu> updateLoaiTaiLieu(@PathVariable int id, @RequestBody LoaiTaiLieu loaiTaiLieuDetails) {
        try {
            LoaiTaiLieu updatedLoaiTaiLieu = loaiTaiLieuService.updateLoaiTaiLieu(id, loaiTaiLieuDetails);
            return ResponseEntity.ok(updatedLoaiTaiLieu);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiTaiLieu(@PathVariable int id) {
        try {
            loaiTaiLieuService.deleteLoaiTaiLieu(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}