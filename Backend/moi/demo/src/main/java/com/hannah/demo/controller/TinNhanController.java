package com.hannah.demo.controller;

import com.hannah.demo.model.TinNhan;
import com.hannah.demo.service.TinNhanService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tinnhan")
public class TinNhanController {
    @Autowired
    private TinNhanService tinNhanService;

    @GetMapping
    public List<TinNhan> getAllTinNhan() {
        return tinNhanService.getAllTinNhan();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TinNhan> getTinNhanById(@PathVariable int id) {
        Optional<TinNhan> tinNhan = tinNhanService.getTinNhanById(id);
        return tinNhan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TinNhan createTinNhan(@RequestBody TinNhan tinNhan) {
        return tinNhanService.createTinNhan(tinNhan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TinNhan> updateTinNhan(@PathVariable int id, @RequestBody TinNhan tinNhanDetails) {
        try {
            TinNhan updatedTinNhan = tinNhanService.updateTinNhan(id, tinNhanDetails);
            return ResponseEntity.ok(updatedTinNhan);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTinNhan(@PathVariable int id) {
        try {
            tinNhanService.deleteTinNhan(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}