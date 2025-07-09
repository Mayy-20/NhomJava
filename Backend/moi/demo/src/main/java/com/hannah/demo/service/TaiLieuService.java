package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.TaiLieu;
import com.hannah.demo.repository.TaiLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaiLieuService {
    @Autowired
    private TaiLieuRepository taiLieuRepository;

    public List<TaiLieu> getAllTaiLieu() {
        return taiLieuRepository.findAll();
    }

    public Optional<TaiLieu> getTaiLieuById(int id) {
        return taiLieuRepository.findById(id);
    }

    public List<TaiLieu> getTaiLieuByTrangThai(TaiLieu.TrangThai trangThai) {
        return taiLieuRepository.findByTrangThai(trangThai);
    }

    public List<TaiLieu> getTaiLieuByMaLoaiTaiLieu(Integer maLoaiTaiLieu) {
        return taiLieuRepository.findByMaLoaiTaiLieu_MaLoaiTaiLieu(maLoaiTaiLieu);
    }

    public TaiLieu createTaiLieu(TaiLieu taiLieu, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            taiLieu.setDuongDan(file.getOriginalFilename());
            taiLieu.setKichThuoc(file.getSize());
            taiLieu.setFileContent(file.getBytes());
        }
        taiLieu.setNgayTao(LocalDateTime.now());
        // Giả sử logic nghiệp vụ là tài liệu mới luôn ở trạng thái chờ duyệt
        // Và ENUM trong TaiLieu.java đã được chuẩn hóa
        taiLieu.setTrangThai(TaiLieu.TrangThai.valueOf("PENDING"));
        return taiLieuRepository.save(taiLieu);
    }

    public TaiLieu updateTaiLieu(int id, TaiLieu taiLieuDetails, MultipartFile file) throws IOException {
        TaiLieu taiLieu = taiLieuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaiLieu not found with id: " + id));
        
        taiLieu.setTenTaiLieu(taiLieuDetails.getTenTaiLieu());
        taiLieu.setMaLoaiTaiLieu(taiLieuDetails.getMaLoaiTaiLieu());
        taiLieu.setMaBaiHoc(taiLieuDetails.getMaBaiHoc());
        taiLieu.setMaTacGia(taiLieuDetails.getMaTacGia());
        taiLieu.setTrangThai(taiLieuDetails.getTrangThai());

        if (file != null && !file.isEmpty()) {
            taiLieu.setDuongDan(file.getOriginalFilename());
            taiLieu.setKichThuoc(file.getSize());
            taiLieu.setFileContent(file.getBytes());
        }
        return taiLieuRepository.save(taiLieu);
    }

    public void deleteTaiLieu(int id) {
        TaiLieu taiLieu = taiLieuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaiLieu not found with id: " + id));
        taiLieuRepository.delete(taiLieu);
    }
}