package com.hannah.demo.controller;

import com.hannah.demo.model.NguoiDung;
import com.hannah.demo.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    // API để lấy tất cả người dùng
    @GetMapping
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungService.getAllNguoiDung();
    }

    // API để lấy người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<NguoiDung> getNguoiDungById(@PathVariable(value = "id") int id) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id)
                .orElse(null); // Hoặc ném ngoại lệ
        if(nguoiDung == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(nguoiDung);
    }
    
    // API để lấy danh sách giảng viên (dùng cho form tạo khóa học)
    @GetMapping("/instructors")
    public List<NguoiDung> getInstructors() {
        return nguoiDungService.findAllInstructors();
    }

    // API để tạo người dùng mới
    @PostMapping(consumes = {"multipart/form-data"})
    public NguoiDung createNguoiDung(@RequestPart("user") NguoiDung nguoiDung, 
                                     @RequestPart(value = "avatar", required = false) MultipartFile avatarFile) throws IOException {
        return nguoiDungService.createNguoiDung(nguoiDung, avatarFile);
    }

    // API để cập nhật người dùng
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<NguoiDung> updateNguoiDung(@PathVariable int id, 
                                                   @RequestPart("user") NguoiDung nguoiDungDetails,
                                                   @RequestPart(value = "avatar", required = false) MultipartFile avatarFile) throws IOException {
        NguoiDung updatedNguoiDung = nguoiDungService.updateNguoiDung(id, nguoiDungDetails, avatarFile);
        return ResponseEntity.ok(updatedNguoiDung);
    }
}