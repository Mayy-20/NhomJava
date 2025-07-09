package com.hannah.demo.controller;

import com.hannah.demo.model.PhienChat;
import com.hannah.demo.service.PhienChatService;
import com.hannah.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/phienchat")
public class PhienChatController {
    @Autowired
    private PhienChatService phienChatService;

    @GetMapping
    public List<PhienChat> getAllPhienChat() {
        return phienChatService.getAllPhienChat();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhienChat> getPhienChatById(@PathVariable int id) {
        Optional<PhienChat> phienChat = phienChatService.getPhienChatById(id);
        return phienChat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PhienChat createPhienChat(@RequestBody PhienChat phienChat) {
        return phienChatService.createPhienChat(phienChat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhienChat> updatePhienChat(@PathVariable int id, @RequestBody PhienChat phienChatDetails) {
        try {
            PhienChat updatedPhienChat = phienChatService.updatePhienChat(id, phienChatDetails);
            return ResponseEntity.ok(updatedPhienChat);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhienChat(@PathVariable int id) {
        try {
            phienChatService.deletePhienChat(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}