package ut.edu.admin.controller;

import ut.edu.admin.dto.NguoiDungDTO;
import ut.edu.admin.service.NguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class NguoiDungController {
    private final NguoiDungService nguoiDungService;

    @GetMapping
    public ResponseEntity<List<NguoiDungDTO>> getAllUsers() {
        return ResponseEntity.ok(nguoiDungService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NguoiDungDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(nguoiDungService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NguoiDungDTO> updateUser(@PathVariable Integer id, @RequestBody NguoiDungDTO dto) {
        return ResponseEntity.ok(nguoiDungService.updateUser(id, dto));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Integer id) {
        nguoiDungService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        nguoiDungService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recent")
    public ResponseEntity<List<NguoiDungDTO>> getRecentUsers(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(nguoiDungService.getRecentUsers(limit));
    }
}