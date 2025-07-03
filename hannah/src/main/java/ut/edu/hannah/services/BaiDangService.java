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

    public BaiDang createBaiDang(String tieuDe, String noiDung, Integer maTacGia, Integer maChuDe, Integer maBaiHoc) {
        if (tieuDe == null || tieuDe.trim().isEmpty()) {
            throw new IllegalArgumentException("Tiêu đề bài đăng không được để trống");
        }
        if (noiDung == null || noiDung.trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung bài đăng không được để trống");
        }
        if (maTacGia == null) {
            throw new IllegalArgumentException("Mã tác giả không được để trống");
        }

        NguoiDung tacGia = nguoiDungRepository.findById(maTacGia)
                .orElseThrow(() -> new IllegalArgumentException("Tác giả không tồn tại"));

        BaiDang baiDang = new BaiDang();
        baiDang.setTieuDe(tieuDe);
        baiDang.setNoiDung(noiDung);
        baiDang.setTacGia(tacGia); // ✅ set entity thay vì ID

        if (maChuDe != null) {
            ChuDe chuDe = chuDeRepository.findById(maChuDe)
                    .orElseThrow(() -> new IllegalArgumentException("Chủ đề không tồn tại"));
            baiDang.setChuDe(chuDe); // ✅
        }

        if (maBaiHoc != null) {
            BaiHoc baiHoc = baiHocRepository.findById(maBaiHoc)
                    .orElseThrow(() -> new IllegalArgumentException("Bài học không tồn tại"));
            baiDang.setBaiHoc(baiHoc); // ✅
        }

        return baiDangRepository.save(baiDang);
    }

    public List<BaiDang> findByChuDe(Integer maChuDe) {
        if (maChuDe == null) {
            throw new IllegalArgumentException("Mã chủ đề không được để trống");
        }

        ChuDe chuDe = chuDeRepository.findById(maChuDe)
                .orElseThrow(() -> new IllegalArgumentException("Chủ đề không tồn tại"));

        return baiDangRepository.findByChuDe(chuDe); // ✅ truyền entity
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

    public Object findByKhoaHoc(Integer maKhoaHoc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByKhoaHoc'");
    }
}