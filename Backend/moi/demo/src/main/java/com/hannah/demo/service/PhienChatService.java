package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.PhienChat;
import com.hannah.demo.repository.PhienChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhienChatService {
    @Autowired
    private PhienChatRepository phienChatRepository;

    public List<PhienChat> getAllPhienChat() {
        return phienChatRepository.findAll();
    }

    public Optional<PhienChat> getPhienChatById(int id) {
        return phienChatRepository.findById(id);
    }

    public PhienChat createPhienChat(PhienChat phienChat) {
        return phienChatRepository.save(phienChat);
    }

    public PhienChat updatePhienChat(int id, PhienChat phienChatDetails) {
        PhienChat phienChat = phienChatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PhienChat not found with id: " + id));
        phienChat.setMaNguoiDung(phienChatDetails.getMaNguoiDung());
        phienChat.setMaBaiHoc(phienChatDetails.getMaBaiHoc());
        phienChat.setBatDau(phienChatDetails.getBatDau());
        return phienChatRepository.save(phienChat);
    }

    public void deletePhienChat(int id) {
        PhienChat phienChat = phienChatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PhienChat not found with id: " + id));
        phienChatRepository.delete(phienChat);
    }
}