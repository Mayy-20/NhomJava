package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.BinhLuan;
import com.hannah.demo.repository.BinhLuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinhLuanService {
    @Autowired
    private BinhLuanRepository binhLuanRepository;

    public List<BinhLuan> getAllBinhLuan() {
        return binhLuanRepository.findAll();
    }

    public Optional<BinhLuan> getBinhLuanById(int id) {
        return binhLuanRepository.findById(id);
    }

    public BinhLuan createBinhLuan(BinhLuan binhLuan) {
        return binhLuanRepository.save(binhLuan);
    }

    public BinhLuan updateBinhLuan(int id, BinhLuan binhLuanDetails) {
        BinhLuan binhLuan = binhLuanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BinhLuan not found with id: " + id));
        binhLuan.setNoiDung(binhLuanDetails.getNoiDung());
        binhLuan.setMaBaiDang(binhLuanDetails.getMaBaiDang());
        binhLuan.setMaNguoiDung(binhLuanDetails.getMaNguoiDung());
        binhLuan.setNgayTao(binhLuanDetails.getNgayTao());
        return binhLuanRepository.save(binhLuan);
    }

    public void deleteBinhLuan(int id) {
        BinhLuan binhLuan = binhLuanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BinhLuan not found with id: " + id));
        binhLuanRepository.delete(binhLuan);
    }
}