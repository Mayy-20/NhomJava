package com.hannah.demo.controller;

import com.hannah.demo.model.BinhLuan;
import com.hannah.demo.service.BinhLuanService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/binhluan")
public class BinhLuanController {
    @Autowired
    private BinhLuanService binhLuanService;

    @GetMapping
    public List<BinhLuan> getAllBinhLuan() {
        return binhLuanService.getAllBinhLuan();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BinhLuan> getBinhLuanById(@PathVariable int id) {
        Optional<BinhLuan> binhLuan = binhLuanService.getBinhLuanById(id);
        return binhLuan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BinhLuan createBinhLuan(@RequestBody BinhLuan binhLuan) {
        return binhLuanService.createBinhLuan(binhLuan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BinhLuan> updateBinhLuan(@PathVariable int id, @RequestBody BinhLuan binhLuanDetails) {
        try {
            BinhLuan updatedBinhLuan = binhLuanService.updateBinhLuan(id, binhLuanDetails);
            return ResponseEntity.ok(updatedBinhLuan);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBinhLuan(@PathVariable int id) {
        try {
            binhLuanService.deleteBinhLuan(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}