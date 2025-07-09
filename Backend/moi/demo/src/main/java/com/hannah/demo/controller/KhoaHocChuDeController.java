package com.hannah.demo.controller;

import com.hannah.demo.model.KhoaHocChuDe;
import com.hannah.demo.service.KhoaHocChuDeService;
import com.hannah.demo.model.KhoaHocChuDeId;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/khoahoc-chude")
public class KhoaHocChuDeController {
    @Autowired
    private KhoaHocChuDeService khoaHocChuDeService;

    @GetMapping
    public List<KhoaHocChuDe> getAllKhoaHocChuDe() {
        return khoaHocChuDeService.getAllKhoaHocChuDe();
    }

    @GetMapping("/{maKhoaHoc}/{maChuDe}")
    public ResponseEntity<KhoaHocChuDe> getKhoaHocChuDeById(@PathVariable Long maKhoaHoc, @PathVariable Long maChuDe) {
        KhoaHocChuDeId id = new KhoaHocChuDeId(maKhoaHoc, maChuDe);
        Optional<KhoaHocChuDe> khoaHocChuDe = khoaHocChuDeService.getKhoaHocChuDeById(id);
        return khoaHocChuDe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public KhoaHocChuDe createKhoaHocChuDe(@RequestBody KhoaHocChuDe khoaHocChuDe) {
        return khoaHocChuDeService.createKhoaHocChuDe(khoaHocChuDe);
    }

    @DeleteMapping("/{maKhoaHoc}/{maChuDe}")
    public ResponseEntity<Void> deleteKhoaHocChuDe(@PathVariable Long maKhoaHoc, @PathVariable Long maChuDe) {
        try {
            KhoaHocChuDeId id = new KhoaHocChuDeId(maKhoaHoc, maChuDe);
            khoaHocChuDeService.deleteKhoaHocChuDe(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}