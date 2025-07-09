package com.hannah.demo.controller;

import com.hannah.demo.model.NguoiDung;
import com.hannah.demo.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/api/users/{id}/avatar")
    public ResponseEntity<byte[]> getUserAvatar(@PathVariable int id) {
        NguoiDung nguoiDung = nguoiDungService.getNguoiDungById(id)
                .orElse(null);

        if (nguoiDung == null || nguoiDung.getAvatarContent() == null) {
            return ResponseEntity.notFound().build();
        }

        MediaType mediaType = MediaType.parseMediaType(nguoiDung.getAnhDaiDien());

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(nguoiDung.getAvatarContent());
    }
}
