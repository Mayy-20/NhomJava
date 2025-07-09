package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.DangKy;
import com.hannah.demo.repository.DangKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DangKyService {
    @Autowired
    private DangKyRepository dangKyRepository;

    public List<DangKy> getAllDangKy() {
        return dangKyRepository.findAll();
    }

    public Optional<DangKy> getDangKyById(int id) {
        return dangKyRepository.findById(id);
    }

    public DangKy createDangKy(DangKy dangKy) {
        return dangKyRepository.save(dangKy);
    }

    public DangKy updateDangKy(int id, DangKy dangKyDetails) {
        DangKy dangKy = dangKyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DangKy not found with id: " + id));
        dangKy.setMaNguoiDung(dangKyDetails.getMaNguoiDung());
        dangKy.setMaKhoaHoc(dangKyDetails.getMaKhoaHoc());
        dangKy.setNgayDangKy(dangKyDetails.getNgayDangKy());
        return dangKyRepository.save(dangKy);
    }

    public void deleteDangKy(int id) {
        DangKy dangKy = dangKyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DangKy not found with id: " + id));
        dangKyRepository.delete(dangKy);
    }
}