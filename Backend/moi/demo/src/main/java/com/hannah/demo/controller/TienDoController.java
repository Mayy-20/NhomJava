package com.hannah.demo.controller;

import com.hannah.demo.model.TienDo;
import com.hannah.demo.service.TienDoService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tiendo")
public class TienDoController {
    @Autowired
    private TienDoService tienDoService;

    @GetMapping
    public List<TienDo> getAllTienDo() {
        return tienDoService.getAllTienDo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TienDo> getTienDoById(@PathVariable int id) {
        Optional<TienDo> tienDo = tienDoService.getTienDoById(id);
        return tienDo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TienDo createTienDo(@RequestBody TienDo tienDo) {
        return tienDoService.createTienDo(tienDo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TienDo> updateTienDo(@PathVariable int id, @RequestBody TienDo tienDoDetails) {
        try {
            TienDo updatedTienDo = tienDoService.updateTienDo(id, tienDoDetails);
            return ResponseEntity.ok(updatedTienDo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTienDo(@PathVariable int id) {
        try {
            tienDoService.deleteTienDo(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}