package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.BinhLuan;
import ut.edu.hannah.model.BaiDang;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.repository.BinhLuanRepository;
import ut.edu.hannah.repository.BaiDangRepository;
import ut.edu.hannah.repository.NguoiDungRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service để quản lý bình luận của bài đăng.
 */
@Service
public class BinhLuanService {
    private final BinhLuanRepository binhLuanRepository;
    private final BaiDangRepository baiDangRepository;
    private final NguoiDungRepository nguoiDungRepository;

    public BinhLuanService(BinhLuanRepository binhLuanRepository, BaiDangRepository baiDangRepository, 
            NguoiDungRepository nguoiDungRepository) {
        this.binhLuanRepository = binhLuanRepository;
        this.baiDangRepository = baiDangRepository;
        this.nguoiDungRepository = nguoiDungRepository;
    }

    /**
     * Tạo bình luận mới.
     * @param noiDung Nội dung bình luận.
     * @param maBaiDang Mã bài đăng.
     * @param maNguoiDung Mã người dùng.
     * @return Bình luận được tạo.
     */
    public BinhLuan createBinhLuan(String noiDung, Integer maBaiDang, Integer maNguoiDung) {
        if (noiDung == null || noiDung.trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung bình luận không được để trống");
        }
        if (maBaiDang == null) {
            throw new IllegalArgumentException("Mã bài đăng không được để trống");
        }
        if (maNguoiDung == null) {
            throw new IllegalArgumentException("Mã người dùng không được để trống");
        }

        BaiDang baiDang = getBaiDang(maBaiDang);
        NguoiDung nguoiDung = getNguoiDung(maNguoiDung);

        BinhLuan binhLuan = new BinhLuan();
        binhLuan.setNoiDung(noiDung);
        binhLuan.setBaiDang(baiDang);
        binhLuan.setNguoiDung(nguoiDung);
        return binhLuanRepository.save(binhLuan);
    }

    /**
     * Tìm bình luận theo khóa học.
     * @param maKhoaHoc Mã khóa học.
     * @return Danh sách bình luận.
     */
    public List<BinhLuan> findByKhoaHoc(Integer maKhoaHoc) {
        if (maKhoaHoc == null) {
            throw new IllegalArgumentException("Mã khóa học không được để trống");
        }
        List<BaiDang> baiDangList = baiDangRepository.findByBaiHocKhoaHocMaKhoaHoc(maKhoaHoc);
        return binhLuanRepository.findByBaiDangIn(baiDangList);
    }

    /**
     * Tìm bình luận theo mã.
     * @param id Mã bình luận.
     * @return Bình luận (nếu có).
     */
    public Optional<BinhLuan> findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Mã bình luận không được để trống");
        }
        return binhLuanRepository.findById(id);
    }

    // Lấy bài đăng theo mã
    private BaiDang getBaiDang(Integer maBaiDang) {
        return baiDangRepository.findById(maBaiDang)
                .orElseThrow(() -> new IllegalArgumentException("Bài đăng không tồn tại"));
    }

    // Lấy người dùng theo mã
    private NguoiDung getNguoiDung(Integer maNguoiDung) {
        return nguoiDungRepository.findById(maNguoiDung)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));
    }
}