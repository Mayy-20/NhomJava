package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.BaiDang;
import ut.edu.hannah.model.BaiDang.TrangThai;
import ut.edu.hannah.model.BaiHoc;
import ut.edu.hannah.model.ChuDe;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.repository.BaiDangRepository;
import ut.edu.hannah.repository.BaiHocRepository;
import ut.edu.hannah.repository.ChuDeRepository;
import ut.edu.hannah.repository.NguoiDungRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service để quản lý các thao tác liên quan đến bài đăng trong cộng đồng.
 */
@Service
public class BaiDangService {
    private final BaiDangRepository baiDangRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final ChuDeRepository chuDeRepository;
    private final BaiHocRepository baiHocRepository;

    public BaiDangService(BaiDangRepository baiDangRepository, NguoiDungRepository nguoiDungRepository,
                         ChuDeRepository chuDeRepository, BaiHocRepository baiHocRepository) {
        this.baiDangRepository = baiDangRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.chuDeRepository = chuDeRepository;
        this.baiHocRepository = baiHocRepository;
    }

    /**
     * Lấy tất cả bài đăng.
     * @return Danh sách tất cả bài đăng.
     */
    public List<BaiDang> getAllBaiDang() {
        return baiDangRepository.findAll();
    }

    /**
     * Tạo bài đăng mới.
     * @param tieuDe Tiêu đề bài đăng.
     * @param noiDung Nội dung bài đăng.
     * @param maTacGia Mã tác giả.
     * @param maChuDe Mã chủ đề (tùy chọn).
     * @param maBaiHoc Mã bài học (tùy chọn).
     * @return Bài đăng đã tạo.
     */
    public BaiDang createBaiDang(String tieuDe, String noiDung, Integer maTacGia, Integer maChuDe, Integer maBaiHoc) {
        NguoiDung tacGia = nguoiDungRepository.findById(maTacGia)
                .orElseThrow(() -> new IllegalArgumentException("Tác giả không tồn tại"));

        BaiDang baiDang = new BaiDang();
        baiDang.setTieuDe(tieuDe);
        baiDang.setNoiDung(noiDung);
        baiDang.setTacGia(tacGia);
        baiDang.setTrangThai(BaiDang.TrangThai.ChoDuyet); 

        if (maChuDe != null) {
            ChuDe chuDe = chuDeRepository.findById(maChuDe)
                    .orElseThrow(() -> new IllegalArgumentException("Chủ đề không tồn tại"));
            baiDang.setChuDe(chuDe);
        }

        if (maBaiHoc != null) {
            BaiHoc baiHoc = baiHocRepository.findById(maBaiHoc)
                    .orElseThrow(() -> new IllegalArgumentException("Bài học không tồn tại"));
            baiDang.setBaiHoc(baiHoc);
        }

        return baiDangRepository.save(baiDang);
    }
  public List<BaiDang> findByChuDe(Integer maChuDe) {
    if (maChuDe == null) {
        throw new IllegalArgumentException("Mã chủ đề không được để trống");
    }

    ChuDe chuDe = chuDeRepository.findById(maChuDe)
            .orElseThrow(() -> new IllegalArgumentException("Chủ đề không tồn tại"));

    return baiDangRepository.findByChuDeAndTrangThai(chuDe, BaiDang.TrangThai.DaDuyet);
}

    public Optional<BaiDang> findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Mã bài đăng không được để trống");
        }
        return baiDangRepository.findById(id);
    }

    public List<BaiDang> findByTrangThai(TrangThai trangThai) {
        return baiDangRepository.findByTrangThai(trangThai);
    }

 public List<BaiDang> findByKhoaHoc(Integer maKhoaHoc) {
    if (maKhoaHoc == null) {
        throw new IllegalArgumentException("Mã khóa học không được để trống");
    }
    List<BaiHoc> baiHocList = baiHocRepository.findByKhoaHoc_MaKhoaHoc(maKhoaHoc);
    return baiDangRepository.findByBaiHocIn(baiHocList);
}
public List<BaiDang> getBaiDangDaDuyet() {
    return baiDangRepository.findByTrangThai(BaiDang.TrangThai.DaDuyet);
}
}