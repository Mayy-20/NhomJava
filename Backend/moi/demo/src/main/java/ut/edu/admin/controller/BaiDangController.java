package ut.edu.admin.controller;

import ut.edu.admin.dto.BaiDangDTO;
import ut.edu.admin.service.BaiDangService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class BaiDangController {
    private final BaiDangService baiDangService;

    @GetMapping
    public ResponseEntity<Page<BaiDangDTO>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(baiDangService.getAllPosts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaiDangDTO> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok(baiDangService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<BaiDangDTO> createPost(@RequestBody BaiDangDTO dto) {
        return ResponseEntity.ok(baiDangService.createPost(dto));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<BaiDangDTO> approvePost(@PathVariable Integer id) {
        return ResponseEntity.ok(baiDangService.approvePost(id));
    }

    @PostMapping("/{id}/hide")
    public ResponseEntity<BaiDangDTO> hidePost(@PathVariable Integer id) {
        return ResponseEntity.ok(baiDangService.hidePost(id));
    }
}