package com.hannah.demo.controller;

import com.hannah.demo.model.TaiLieu;
import com.hannah.demo.service.TaiLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    private TaiLieuService taiLieuService;

    @GetMapping("/api/documents/{id}/download")
    public ResponseEntity<byte[]> downloadTaiLieu(@PathVariable int id) {
        TaiLieu taiLieu = taiLieuService.getTaiLieuById(id)
                .orElse(null);

        if (taiLieu == null || taiLieu.getFileContent() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + taiLieu.getDuongDan() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(taiLieu.getFileContent());
    }
}
