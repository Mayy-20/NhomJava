package com.hannah.demo.controller;

import com.hannah.demo.model.VaiTro;
import com.hannah.demo.service.VaiTroService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vaitro")
public class VaiTroController {
    @Autowired
    private VaiTroService vaiTroService;

    @GetMapping
    public List<VaiTro> getAllVaiTro() {
        return vaiTroService.getAllVaiTro();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaiTro> getVaiTroById(@PathVariable int id) {
        Optional<VaiTro> vaiTro = vaiTroService.getVaiTroById(id);
        return vaiTro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public VaiTro createVaiTro(@RequestBody VaiTro vaiTro) {
        return vaiTroService.createVaiTro(vaiTro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaiTro> updateVaiTro(@PathVariable int id, @RequestBody VaiTro vaiTroDetails) {
        try {
            VaiTro updatedVaiTro = vaiTroService.updateVaiTro(id, vaiTroDetails);
            return ResponseEntity.ok(updatedVaiTro);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaiTro(@PathVariable int id) {
        try {
            vaiTroService.deleteVaiTro(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}