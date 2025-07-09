package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.KhoaHoc;
import com.hannah.demo.repository.KhoaHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhoaHocService {
    @Autowired
    private KhoaHocRepository khoaHocRepository;

    public List<KhoaHoc> getAllKhoaHoc() {
        return khoaHocRepository.findAll();
    }

    public Optional<KhoaHoc> getKhoaHocById(int id) {
        return khoaHocRepository.findById(id);
    }
    
    public KhoaHoc createKhoaHoc(KhoaHoc khoaHoc) {
        return khoaHocRepository.save(khoaHoc);
    }
    
    public KhoaHoc updateKhoaHoc(int id, KhoaHoc khoaHocDetails) {
        KhoaHoc khoaHoc = khoaHocRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KhoaHoc not found with id: " + id));
        
        khoaHoc.setTenKhoaHoc(khoaHocDetails.getTenKhoaHoc());
        khoaHoc.setMoTa(khoaHocDetails.getMoTa());
        khoaHoc.setTrangThai(khoaHocDetails.getTrangThai());
        // ... set các trường khác
        
        return khoaHocRepository.save(khoaHoc);
    }
}