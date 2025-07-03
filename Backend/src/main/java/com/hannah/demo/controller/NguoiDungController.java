package com.hannah.demo.controller;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.dto.NguoiDungDTO;
import com.hannah.demo.model.NguoiDung;
import com.hannah.demo.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin-users")
@CrossOrigin(origins = "*")
public class NguoiDungController {
    
    @Autowired
    private NguoiDungService nguoiDungService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<NguoiDungDTO>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer role) {
        
        ApiResponse<Page<NguoiDungDTO>> response = nguoiDungService.getAllUsers(page, size, search, status, role);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NguoiDungDTO>> getUserById(@PathVariable Integer id) {
        ApiResponse<NguoiDungDTO> response = nguoiDungService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<NguoiDungDTO>> createUser(@RequestBody NguoiDung nguoiDung) {
        ApiResponse<NguoiDungDTO> response = nguoiDungService.createUser(nguoiDung);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NguoiDungDTO>> updateUser(@PathVariable Integer id, 
                                                               @RequestBody NguoiDung nguoiDung) {
        ApiResponse<NguoiDungDTO> response = nguoiDungService.updateUser(id, nguoiDung);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Integer id) {
        ApiResponse<String> response = nguoiDungService.deleteUser(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<String>> approveUser(@PathVariable Integer id) {
        ApiResponse<String> response = nguoiDungService.approveUser(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<String>> rejectUser(@PathVariable Integer id, 
                                                         @RequestBody Map<String, String> request) {
        String reason = request.get("reason");
        ApiResponse<String> response = nguoiDungService.rejectUser(id, reason);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<String>> activateUser(@PathVariable Integer id) {
        ApiResponse<String> response = nguoiDungService.approveUser(id);
        return ResponseEntity.ok(response);
    }
}
