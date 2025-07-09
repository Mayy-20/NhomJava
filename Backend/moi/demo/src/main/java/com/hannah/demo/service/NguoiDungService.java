package com.hannah.demo.service;

import com.hannah.demo.exception.ResourceNotFoundException;
import com.hannah.demo.model.NguoiDung;
import com.hannah.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungRepository.findAll();
    }

    public Optional<NguoiDung> getNguoiDungById(int id) {
        return nguoiDungRepository.findById(id);
    }

    public List<NguoiDung> findAllInstructors() {
        return nguoiDungRepository.findByMaVaiTro_MaVaiTro(2);
    }
    
    public List<NguoiDung> getNguoiDungByTrangThai(NguoiDung.TrangThai trangThai) {
        return nguoiDungRepository.findByTrangThai(trangThai);
    }

    public List<NguoiDung> getNguoiDungByMaVaiTro(Integer maVaiTro) {
        return nguoiDungRepository.findByMaVaiTro_MaVaiTro(maVaiTro);
    }
    
    public NguoiDung createNguoiDung(NguoiDung nguoiDung, MultipartFile avatarFile) throws IOException {
        if (avatarFile != null && !avatarFile.isEmpty()) {
            nguoiDung.setAnhDaiDien(avatarFile.getContentType()); // Lưu content type để hiển thị
            nguoiDung.setAvatarContent(avatarFile.getBytes());  // Lưu nội dung file vào DB
        }
        return nguoiDungRepository.save(nguoiDung);
    }

    public NguoiDung updateNguoiDung(int id, NguoiDung nguoiDungDetails, MultipartFile avatarFile) throws IOException {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NguoiDung not found with id: " + id));
        
        nguoiDung.setHoTen(nguoiDungDetails.getHoTen());
        nguoiDung.setEmail(nguoiDungDetails.getEmail());
        nguoiDung.setDienThoai(nguoiDungDetails.getDienThoai());
        nguoiDung.setGioiThieu(nguoiDungDetails.getGioiThieu());
        nguoiDung.setTrangThai(nguoiDungDetails.getTrangThai());
        nguoiDung.setMaVaiTro(nguoiDungDetails.getMaVaiTro());
        
        if (avatarFile != null && !avatarFile.isEmpty()) {
             nguoiDung.setAnhDaiDien(avatarFile.getContentType());
             nguoiDung.setAvatarContent(avatarFile.getBytes());
        }
        
        return nguoiDungRepository.save(nguoiDung);
    }

    public void deleteNguoiDung(int id) {
        nguoiDungRepository.deleteById(id);
    }
}