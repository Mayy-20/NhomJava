package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.KhoaHoc;
import ut.edu.hannah.repository.KhoaHocRepository;
import ut.edu.hannah.repository.ChuDeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service để quản lý khóa học.
 */
@Service
public class KhoaHocService {
    private final KhoaHocRepository khoaHocRepository;
    private final ChuDeRepository chuDeRepository;

    public KhoaHocService(KhoaHocRepository khoaHocRepository, ChuDeRepository chuDeRepository) {
        this.khoaHocRepository = khoaHocRepository;
        this.chuDeRepository = chuDeRepository;
    }

    /**
     * Lấy tất cả khóa học.
     * @return Danh sách khóa học.
     */
    public List<KhoaHoc> getAllKhoaHoc() {
        return khoaHocRepository.findAll();
    }

    /**
     * Tìm khóa học theo mã.
     * @param id Mã khóa học.
     * @return Khóa học (nếu có).
     */
    public Optional<KhoaHoc> findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Mã khóa học không được để trống");
        }
        return khoaHocRepository.findById(id);
    }

    /**
     * Tìm khóa học theo trạng thái.
     * @param trangThai Trạng thái (HoatDong, ChoDuyet, An).
     * @return Danh sách khóa học.
     */
    public List<KhoaHoc> findByTrangThai(KhoaHoc.TrangThai trangThai) {
        if (trangThai == null) {
            throw new IllegalArgumentException("Trạng thái không được để trống");
        }
        return khoaHocRepository.findByTrangThai(trangThai);
    }

    /**
     * Tìm khóa học theo cấp độ.
     * @param capDo Cấp độ (NguoiMoi, TrungCap, NangCao).
     * @return Danh sách khóa học.
     */
    public List<KhoaHoc> findByCapDo(KhoaHoc.CapDo capDo) {
        if (capDo == null) {
            throw new IllegalArgumentException("Cấp độ không được để trống");
        }
        return khoaHocRepository.findByCapDo(capDo);
    }

    /**
     * Tìm khóa học theo giảng viên.
     * @param maGiangVien Mã giảng viên.
     * @return Danh sách khóa học.
     */
    public List<KhoaHoc> findByGiangVien(Integer maGiangVien) {
        if (maGiangVien == null) {
            throw new IllegalArgumentException("Mã giảng viên không được để trống");
        }
        return khoaHocRepository.findByGiangVienMaNguoiDung(maGiangVien);
    }
    /**
     * Tìm khóa học theo chủ đề.
     * @param maChuDe Mã chủ đề.
     * @return Danh sách khóa học.
     */
    public List<KhoaHoc> findByChuDe(Integer maChuDe) {
        if (maChuDe == null) {
            throw new IllegalArgumentException("Mã chủ đề không được để trống");
        }
        return khoaHocRepository.findByChuDes_MaChuDe(maChuDe);
    }
}