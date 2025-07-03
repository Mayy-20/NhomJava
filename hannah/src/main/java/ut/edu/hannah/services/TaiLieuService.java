package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.TaiLieu;
import ut.edu.hannah.model.BaiHoc;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.model.LoaiTaiLieu;
import ut.edu.hannah.repository.TaiLieuRepository;
import ut.edu.hannah.repository.BaiHocRepository;
import ut.edu.hannah.repository.NguoiDungRepository;
import ut.edu.hannah.repository.LoaiTaiLieuRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service để quản lý tài liệu học tập.
 */
@Service
public class TaiLieuService {
    private final TaiLieuRepository taiLieuRepository;
    private final BaiHocRepository baiHocRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final LoaiTaiLieuRepository loaiTaiLieuRepository;

    public TaiLieuService(TaiLieuRepository taiLieuRepository, BaiHocRepository baiHocRepository, 
            NguoiDungRepository nguoiDungRepository, LoaiTaiLieuRepository loaiTaiLieuRepository) {
        this.taiLieuRepository = taiLieuRepository;
        this.baiHocRepository = baiHocRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.loaiTaiLieuRepository = loaiTaiLieuRepository;
    }

    /**
     * Tạo tài liệu mới.
     * @param tenTaiLieu Tên tài liệu.
     * @param maLoaiTaiLieu Mã loại tài liệu.
     * @param maBaiHoc Mã bài học.
     * @param maTacGia Mã tác giả.
     * @param duongDan Đường dẫn file.
     * @param kichThuoc Kích thước file.
     * @return Tài liệu được tạo.
     */
    public TaiLieu createTaiLieu(String tenTaiLieu, Integer maLoaiTaiLieu, Integer maBaiHoc, Integer maTacGia, String duongDan, Long kichThuoc) {
        if (tenTaiLieu == null || tenTaiLieu.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tài liệu không được để trống");
        }
        if (maLoaiTaiLieu == null || maBaiHoc == null || maTacGia == null) {
            throw new IllegalArgumentException("Mã loại tài liệu, bài học hoặc tác giả không được để trống");
        }
        if (kichThuoc != null && kichThuoc < 0) {
            throw new IllegalArgumentException("Kích thước file không hợp lệ");
        }

        LoaiTaiLieu loaiTaiLieu = getLoaiTaiLieu(maLoaiTaiLieu);
        BaiHoc baiHoc = getBaiHoc(maBaiHoc);
        NguoiDung tacGia = getNguoiDung(maTacGia);

        TaiLieu taiLieu = new TaiLieu();
        taiLieu.setTenTaiLieu(tenTaiLieu);
        taiLieu.setLoaiTaiLieu(loaiTaiLieu);
        taiLieu.setBaiHoc(baiHoc);
        taiLieu.setTacGia(tacGia);
        taiLieu.setDuongDan(duongDan);
        taiLieu.setKichThuoc(kichThuoc);
        return taiLieuRepository.save(taiLieu);
    }

    /**
     * Tìm tài liệu theo bài học.
     * @param maBaiHoc Mã bài học.
     * @return Danh sách tài liệu.
     */
    public List<TaiLieu> findByBaiHoc(Integer maBaiHoc) {
        if (maBaiHoc == null) {
            throw new IllegalArgumentException("Mã bài học không được để trống");
        }
        return taiLieuRepository.findByBaiHocMaBaiHoc(maBaiHoc);
    }

    /**
     * Tìm tài liệu theo mã.
     * @param id Mã tài liệu.
     * @return Tài liệu (nếu có).
     */
    public Optional<TaiLieu> findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Mã tài liệu không được để trống");
        }
        return taiLieuRepository.findById(id);
    }

    // Lấy loại tài liệu theo mã
    private LoaiTaiLieu getLoaiTaiLieu(Integer maLoaiTaiLieu) {
        return loaiTaiLieuRepository.findById(maLoaiTaiLieu)
                .orElseThrow(() -> new IllegalArgumentException("Loại tài liệu không tồn tại"));
    }

    // Lấy bài học theo mã
    private BaiHoc getBaiHoc(Integer maBaiHoc) {
        return baiHocRepository.findById(maBaiHoc)
                .orElseThrow(() -> new IllegalArgumentException("Bài học không tồn tại"));
    }

    // Lấy người dùng theo mã
    private NguoiDung getNguoiDung(Integer maTacGia) {
        return nguoiDungRepository.findById(maTacGia)
                .orElseThrow(() -> new IllegalArgumentException("Tác giả không tồn tại"));
    }
}