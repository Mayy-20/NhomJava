package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.LoaiTaiLieu;
import ut.edu.hannah.repository.LoaiTaiLieuRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service để quản lý các thao tác liên quan đến loại tài liệu.
 */
@Service
public class LoaiTaiLieuService {
    private final LoaiTaiLieuRepository loaiTaiLieuRepository;

    public LoaiTaiLieuService(LoaiTaiLieuRepository loaiTaiLieuRepository) {
        this.loaiTaiLieuRepository = loaiTaiLieuRepository;
    }

    public List<LoaiTaiLieu> getAllLoaiTaiLieu() {
        return loaiTaiLieuRepository.findAll();
    }

    public Optional<LoaiTaiLieu> findByTenLoai(String tenLoai) {
        if (tenLoai == null || tenLoai.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên loại tài liệu không được để trống");
        }
        return Optional.ofNullable(loaiTaiLieuRepository.findByTenLoai(tenLoai));
    }
}