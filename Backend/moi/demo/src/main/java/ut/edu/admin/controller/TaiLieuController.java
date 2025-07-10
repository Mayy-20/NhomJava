package ut.edu.admin.controller;

import ut.edu.admin.dto.TaiLieuDTO;
import ut.edu.admin.service.TaiLieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class TaiLieuController {
    private final TaiLieuService taiLieuService;

    @GetMapping
    public ResponseEntity<List<TaiLieuDTO>> getAllDocuments() {
        return ResponseEntity.ok(taiLieuService.getAllDocuments());
    }

    @PostMapping
    public ResponseEntity<TaiLieuDTO> createDocument(@RequestBody TaiLieuDTO dto) {
        return ResponseEntity.ok(taiLieuService.createDocument(dto));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<TaiLieuDTO> approveDocument(@PathVariable Integer id) {
        return ResponseEntity.ok(taiLieuService.approveDocument(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<TaiLieuDTO> rejectDocument(@PathVariable Integer id) {
        return ResponseEntity.ok(taiLieuService.rejectDocument(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Integer id) {
        taiLieuService.deleteDocument(id);
        return ResponseEntity.ok().build();
    }
}
