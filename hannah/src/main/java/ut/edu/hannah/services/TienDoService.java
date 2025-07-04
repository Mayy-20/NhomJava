package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.TienDo;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.model.KhoaHoc;
import ut.edu.hannah.model.BaiHoc;
import ut.edu.hannah.repository.TienDoRepository;
import ut.edu.hannah.repository.NguoiDungRepository;
import ut.edu.hannah.repository.KhoaHocRepository;
import ut.edu.hannah.repository.BaiHocRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service để quản lý tiến độ học tập của người dùng.
 */
@Service
public class TienDoService {
    private final TienDoRepository tienDoRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final KhoaHocRepository khoaHocRepository;
    private final BaiHocRepository baiHocRepository;

    public TienDoService(TienDoRepository tienDoRepository, NguoiDungRepository nguoiDungRepository, 
            KhoaHocRepository khoaHocRepository, BaiHocRepository baiHocRepository) {
        this.tienDoRepository = tienDoRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.khoaHocRepository = khoaHocRepository;
        this.baiHocRepository = baiHocRepository;
    }

    /**
     * Cập nhật tiến độ học tập.
     * @param maNguoiDung Mã người dùng.
     * @param maKhoaHoc Mã khóa học.
     * @param maBaiHoc Mã bài học.
     * @param phanTram Phần trăm hoàn thành.
     * @param thoiGianHoc Thời gian học (giây).
     * @param b 
     * @return Tiến độ đã cập nhật.
     */
    public TienDo updateProgress(Integer maNguoiDung, Integer maKhoaHoc, Integer maBaiHoc, Float phanTram, Integer thoiGianHoc, boolean b) {
        if (maNguoiDung == null || maKhoaHoc == null || maBaiHoc == null) {
            throw new IllegalArgumentException("Mã người dùng, khóa học hoặc bài học không được để trống");
        }
        if (phanTram == null || phanTram < 0 || phanTram > 100) {
            throw new IllegalArgumentException("Phần trăm hoàn thành phải từ 0 đến 100");
        }

        NguoiDung nguoiDung = getNguoiDung(maNguoiDung);
        KhoaHoc khoaHoc = getKhoaHoc(maKhoaHoc);
        BaiHoc baiHoc = getBaiHoc(maBaiHoc);

        TienDo tienDo = findOrCreateTienDo(nguoiDung, khoaHoc, baiHoc);
        updateTienDoDetails(tienDo, phanTram, thoiGianHoc);

        return tienDoRepository.save(tienDo);
    }

    /**
     * Tìm tiến độ theo người dùng.
     * @param maNguoiDung Mã người dùng.
     * @return Danh sách tiến độ.
     */
    public List<TienDo> findByNguoiDung(Integer maNguoiDung) {
        if (maNguoiDung == null) {
            throw new IllegalArgumentException("Mã người dùng không được để trống");
        }
        return tienDoRepository.findByNguoiDungMaNguoiDung(maNguoiDung);
    }

    /**
     * Tìm tiến độ theo người dùng, khóa học, bài học.
     * @param maNguoiDung Mã người dùng.
     * @param maKhoaHoc Mã khóa học.
     * @param maBaiHoc Mã bài học.
     * @return Tiến độ (nếu có).
     */
    public Optional<TienDo> findByNguoiDungAndKhoaHocAndBaiHoc(Integer maNguoiDung, Integer maKhoaHoc, Integer maBaiHoc) {
        if (maNguoiDung == null || maKhoaHoc == null || maBaiHoc == null) {
            throw new IllegalArgumentException("Mã người dùng, khóa học hoặc bài học không được để trống");
        }
        NguoiDung nguoiDung = getNguoiDung(maNguoiDung);
        KhoaHoc khoaHoc = getKhoaHoc(maKhoaHoc);
        BaiHoc baiHoc = getBaiHoc(maBaiHoc);
        return tienDoRepository.findByNguoiDungAndKhoaHocAndBaiHoc(nguoiDung, khoaHoc, baiHoc);
    }

    // Lấy người dùng theo mã
    private NguoiDung getNguoiDung(Integer maNguoiDung) {
        return nguoiDungRepository.findById(maNguoiDung)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));
    }

    // Lấy khóa học theo mã
    private KhoaHoc getKhoaHoc(Integer maKhoaHoc) {
        return khoaHocRepository.findById(maKhoaHoc)
                .orElseThrow(() -> new IllegalArgumentException("Khóa học không tồn tại"));
    }

    // Lấy bài học theo mã
    private BaiHoc getBaiHoc(Integer maBaiHoc) {
        return baiHocRepository.findById(maBaiHoc)
                .orElseThrow(() -> new IllegalArgumentException("Bài học không tồn tại"));
    }

    // Tìm hoặc tạo tiến độ
   private TienDo findOrCreateTienDo(NguoiDung nguoiDung, KhoaHoc khoaHoc, BaiHoc baiHoc) {
    return tienDoRepository.findByNguoiDungAndKhoaHocAndBaiHoc(nguoiDung, khoaHoc, baiHoc)
            .orElseGet(() -> {
                TienDo td = new TienDo();
                td.setNguoiDung(nguoiDung);
                td.setKhoaHoc(khoaHoc);
                td.setBaiHoc(baiHoc); 
                return td;
            });
}


    // Cập nhật chi tiết tiến độ
    private void updateTienDoDetails(TienDo tienDo, Float phanTram, Integer thoiGianHoc) {
        tienDo.setPhanTram(phanTram);
        tienDo.setThoiGianHoc(thoiGianHoc);
        tienDo.setHoanThanh(phanTram >= 100);
        tienDo.setLanCuoiHoc(LocalDateTime.now());
    }

  public TienDo findByKhoaHocAndNguoiDung(Integer id, Integer currentUserId) {
    if (id == null || currentUserId == null) {
        throw new IllegalArgumentException("Mã khóa học hoặc người dùng không được để trống");
    }
    NguoiDung nguoiDung = getNguoiDung(currentUserId);
    KhoaHoc khoaHoc = getKhoaHoc(id);
    List<BaiHoc> baiHocList = baiHocRepository.findByKhoaHoc_MaKhoaHoc(id);
    if (baiHocList.isEmpty()) {
        throw new IllegalArgumentException("Khóa học không có bài học");
    }
    BaiHoc baiHoc = baiHocList.get(0); // Lấy bài học đầu tiên
    return tienDoRepository.findByNguoiDungAndKhoaHocAndBaiHoc(nguoiDung, khoaHoc, baiHoc)
            .orElse(new TienDo(nguoiDung, khoaHoc, baiHoc));
}
public Map<Integer, TienDo> getAllByKhoaHocAndNguoiDungAsMap(Integer khoaHocId, Integer nguoiDungId) {
    KhoaHoc khoaHoc = getKhoaHoc(khoaHocId);
    NguoiDung nguoiDung = getNguoiDung(nguoiDungId);

    List<TienDo> tienDoList = tienDoRepository.findByKhoaHocAndNguoiDung(khoaHoc, nguoiDung);

    return tienDoList.stream()
        .collect(Collectors.toMap(td -> td.getBaiHoc().getMaBaiHoc(), td -> td));
}

}