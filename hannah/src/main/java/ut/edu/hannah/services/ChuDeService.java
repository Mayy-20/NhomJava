package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.ChuDe;
import ut.edu.hannah.repository.ChuDeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service để quản lý các thao tác liên quan đến chủ đề của khóa học.
 */
@Service
public class ChuDeService {
    private final ChuDeRepository chuDeRepository;

    public ChuDeService(ChuDeRepository chuDeRepository) {
        this.chuDeRepository = chuDeRepository;
    }

    public List<ChuDe> getAllChuDe() {
        return chuDeRepository.findAll();
    }

    public Optional<ChuDe> findByTenChuDe(String tenChuDe) {
        if (tenChuDe == null || tenChuDe.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên chủ đề không được để trống");
        }
        return Optional.ofNullable(chuDeRepository.findByTenChuDe(tenChuDe));
    }

    public String findById(Integer maChuDe) {
        if (maChuDe == null) {
            throw new IllegalArgumentException("Mã chủ đề không được để trống");
        }
        return chuDeRepository.findById(maChuDe)
                .map(ChuDe::getTenChuDe)
                .orElseThrow(() -> new IllegalArgumentException("Chủ đề không tồn tại"));
    }
}