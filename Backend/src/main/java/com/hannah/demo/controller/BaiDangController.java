package com.hannah.demo.controller;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.dto.BaiDangDTO;
import com.hannah.demo.service.BaiDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin-community")
@CrossOrigin(origins = "*")
public class BaiDangController {
    
    @Autowired
    private BaiDangService baiDangService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<BaiDangDTO>>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer category) {
        
        ApiResponse<Page<BaiDangDTO>> response = baiDangService.getAllPosts(page, size, search, status, category);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BaiDangDTO>> getPostById(@PathVariable Integer id) {
        ApiResponse<BaiDangDTO> response = baiDangService.getPostById(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<String>> approvePost(@PathVariable Integer id) {
        ApiResponse<String> response = baiDangService.approvePost(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/hide")
    public ResponseEntity<ApiResponse<String>> hidePost(@PathVariable Integer id) {
        ApiResponse<String> response = baiDangService.hidePost(id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletePost(@PathVariable Integer id) {
        ApiResponse<String> response = baiDangService.deletePost(id);
        return ResponseEntity.ok(response);
    }
}
