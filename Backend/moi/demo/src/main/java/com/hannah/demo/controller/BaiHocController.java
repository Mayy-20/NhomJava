package com.hannah.demo.controller;

import com.hannah.demo.model.BaiHoc;
import com.hannah.demo.service.BaiHocService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/baihoc")
public class BaiHocController {
    @Autowired
    private BaiHocService baiHocService;

    @GetMapping
    public List<BaiHoc> getAllBaiHoc() {
        return baiHocService.getAllBaiHoc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaiHoc> getBaiHocById(@PathVariable int id) {
        Optional<BaiHoc> baiHoc = baiHocService.getBaiHocById(id);
        return baiHoc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BaiHoc createBaiHoc(@RequestBody BaiHoc baiHoc) {
        return baiHocService.createBaiHoc(baiHoc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaiHoc> updateBaiHoc(@PathVariable int id, @RequestBody BaiHoc baiHocDetails) {
        try {
            BaiHoc updatedBaiHoc = baiHocService.updateBaiHoc(id, baiHocDetails);
            return ResponseEntity.ok(updatedBaiHoc);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaiHoc(@PathVariable int id) {
        try {
            baiHocService.deleteBaiHoc(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}