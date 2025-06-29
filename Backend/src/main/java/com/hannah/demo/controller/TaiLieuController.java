package com.hannah.demo.controller;

import com.hannah.demo.dto.ApiResponse;
import com.hannah.demo.dto.TaiLieuDTO;
import com.hannah.demo.model.TaiLieu;
import com.hannah.demo.service.TaiLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/api/admin-documents")
@CrossOrigin(origins = "*")
public class TaiLieuController {
    
    @Autowired
    private TaiLieuService taiLieuService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<Page<TaiLieuDTO>>> getAllDocuments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer author) {
        
        ApiResponse<Page<TaiLieuDTO>> response = taiLieuService.getAllDocuments(page, size, search, status, author);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaiLieuDTO>> getDocumentById(@PathVariable Integer id) {
        ApiResponse<TaiLieuDTO> response = taiLieuService.getDocumentById(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<TaiLieuDTO>> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("maBaiHoc") Integer maBaiHoc,
            @RequestParam("maTacGia") Integer maTacGia,
            @RequestParam(value = "maLoaiTaiLieu", defaultValue = "1") Integer maLoaiTaiLieu) {
        
        TaiLieu taiLieu = new TaiLieu();
        taiLieu.setMaBaiHoc(maBaiHoc);
        taiLieu.setMaTacGia(maTacGia);
        taiLieu.setMaLoaiTaiLieu(maLoaiTaiLieu);
        
        ApiResponse<TaiLieuDTO> response = taiLieuService.uploadDocument(file, taiLieu);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Integer id) {
        try {
            ApiResponse<TaiLieuDTO> documentResponse = taiLieuService.getDocumentById(id);
            if (!documentResponse.isSuccess()) {
                return ResponseEntity.notFound().build();
            }
            
            TaiLieuDTO document = documentResponse.getData();
            Path filePath = Paths.get(document.getDuongDan());
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, 
                               "attachment; filename=\"" + document.getTenTaiLieu() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<String>> approveDocument(@PathVariable Integer id) {
        ApiResponse<String> response = taiLieuService.approveDocument(id);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<String>> rejectDocument(@PathVariable Integer id, 
                                                             @RequestBody Map<String, String> request) {
        String reason = request.get("reason");
        ApiResponse<String> response = taiLieuService.rejectDocument(id, reason);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDocument(@PathVariable Integer id) {
        ApiResponse<String> response = taiLieuService.deleteDocument(id);
        return ResponseEntity.ok(response);
    }
}
