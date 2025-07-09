package com.hannah.demo.controller;

import com.hannah.demo.model.BaiDang;
import com.hannah.demo.service.BaiDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class BaiDangController {

    @Autowired
    private BaiDangService baiDangService;

    @GetMapping
    public ResponseEntity<Page<BaiDang>> getAllPosts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category,
            Pageable pageable) {
        Page<BaiDang> posts = baiDangService.findAll(search, status, category, pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaiDang> getPostById(@PathVariable Integer id) {
        BaiDang post = baiDangService.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<BaiDang> createPost(@RequestBody BaiDang baiDang) {
        BaiDang createdPost = baiDangService.createPost(baiDang);
        return ResponseEntity.ok(createdPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaiDang> updatePost(@PathVariable Integer id, @RequestBody BaiDang postDetails) {
        BaiDang updatedPost = baiDangService.updatePost(id, postDetails);
        return ResponseEntity.ok(updatedPost);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        baiDangService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<?> approvePost(@PathVariable Integer id) {
        baiDangService.approvePost(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/hide")
    public ResponseEntity<?> hidePost(@PathVariable Integer id) {
        baiDangService.hidePost(id);
        return ResponseEntity.ok().build();
    }
}