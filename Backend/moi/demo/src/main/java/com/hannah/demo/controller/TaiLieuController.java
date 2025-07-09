package com.hannah.demo.controller;

import com.hannah.demo.model.TaiLieu;
import com.hannah.demo.service.TaiLieuService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tailieu")
public class TaiLieuController {
    @Autowired
    private TaiLieuService taiLieuService;

    @GetMapping
    public List<TaiLieu> getAllTaiLieu() {
        return taiLieuService.getAllTaiLieu();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaiLieu> getTaiLieuById(@PathVariable int id) {
        Optional<TaiLieu> taiLieu = taiLieuService.getTaiLieuById(id);
        return taiLieu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/trangthai/{trangThai}")
    public List<TaiLieu> getTaiLieuByTrangThai(@PathVariable String trangThai) {
        return taiLieuService.getTaiLieuByTrangThai(TaiLieu.TrangThai.valueOf(trangThai));
    }

    @GetMapping("/loaitailieu/{maLoaiTaiLieu}")
    public List<TaiLieu> getTaiLieuByMaLoaiTaiLieu(@PathVariable Integer maLoaiTaiLieu) {
        return taiLieuService.getTaiLieuByMaLoaiTaiLieu(maLoaiTaiLieu);
    }

    @PostMapping
    public ResponseEntity<TaiLieu> createTaiLieu(@RequestPart("taiLieu") TaiLieu taiLieu, 
                                                @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        TaiLieu createdTaiLieu = taiLieuService.createTaiLieu(taiLieu, file);
        return ResponseEntity.ok(createdTaiLieu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaiLieu> updateTaiLieu(@PathVariable int id, 
                                                @RequestPart("taiLieu") TaiLieu taiLieuDetails, 
                                                @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        try {
            TaiLieu updatedTaiLieu = taiLieuService.updateTaiLieu(id, taiLieuDetails, file);
            return ResponseEntity.ok(updatedTaiLieu);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaiLieu(@PathVariable int id) {
        try {
            taiLieuService.deleteTaiLieu(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}