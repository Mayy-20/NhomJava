package ut.edu.admin.controller;

import ut.edu.admin.dto.BaoCaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @GetMapping("/posts/{id}/reports")
    public ResponseEntity<List<BaoCaoDTO>> getReportsByPostId(@PathVariable Integer id) {
        // Giả định có bảng báo cáo, hiện tại trả về danh sách rỗng
        return ResponseEntity.ok(List.of());
    }

    @PostMapping("/{id}/resolve")
    public ResponseEntity<Void> resolveReport(@PathVariable Integer id) {
        // Logic xử lý báo cáo
        return ResponseEntity.ok().build();
    }
}