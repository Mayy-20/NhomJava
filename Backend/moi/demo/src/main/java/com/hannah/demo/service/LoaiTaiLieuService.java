package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.LoaiTaiLieu;
import com.hannah.demo.repository.LoaiTaiLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiTaiLieuService {
    @Autowired
    private LoaiTaiLieuRepository loaiTaiLieuRepository;

    public List<LoaiTaiLieu> getAllLoaiTaiLieu() {
        return loaiTaiLieuRepository.findAll();
    }

    public Optional<LoaiTaiLieu> getLoaiTaiLieuById(int id) {
        return loaiTaiLieuRepository.findById(id);
    }

    public LoaiTaiLieu createLoaiTaiLieu(LoaiTaiLieu loaiTaiLieu) {
        return loaiTaiLieuRepository.save(loaiTaiLieu);
    }

    public LoaiTaiLieu updateLoaiTaiLieu(int id, LoaiTaiLieu loaiTaiLieuDetails) {
        LoaiTaiLieu loaiTaiLieu = loaiTaiLieuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoaiTaiLieu not found with id: " + id));
        loaiTaiLieu.setTenLoai(loaiTaiLieuDetails.getTenLoai());
        loaiTaiLieu.setMoTa(loaiTaiLieuDetails.getMoTa());
        return loaiTaiLieuRepository.save(loaiTaiLieu);
    }

    public void deleteLoaiTaiLieu(int id) {
        LoaiTaiLieu loaiTaiLieu = loaiTaiLieuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoaiTaiLieu not found with id: " + id));
        loaiTaiLieuRepository.delete(loaiTaiLieu);
    }
}