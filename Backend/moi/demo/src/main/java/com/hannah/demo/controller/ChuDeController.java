package com.hannah.demo.controller;

import com.hannah.demo.model.ChuDe;
import com.hannah.demo.service.ChuDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ChuDeController {

    @Autowired
    private ChuDeService chuDeService;

    @GetMapping
    public ResponseEntity<List<ChuDe>> getAllCategories() {
        List<ChuDe> categories = chuDeService.getAllChuDe();
        return ResponseEntity.ok(categories);
    }
    
    // ... other endpoints for managing categories if needed ...
}