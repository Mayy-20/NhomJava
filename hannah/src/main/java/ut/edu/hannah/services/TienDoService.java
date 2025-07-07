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
import ut.edu.hannah.dto.UserCourseProgressDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    public TienDo updateProgress(Integer maNguoiDung, Integer maKhoaHoc, Integer maBaiHoc, BigDecimal phanTram, Integer thoiGianHoc, boolean b) {
        if (maNguoiDung == null || maKhoaHoc == null || maBaiHoc == null) {
            throw new IllegalArgumentException("Mã người dùng, khóa học hoặc bài học không được để trống");
        }
        if (phanTram == null || phanTram.compareTo(BigDecimal.ZERO) < 0 || phanTram.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Phần trăm hoàn thành phải từ 0 đến 100");
        }

        NguoiDung nguoiDung = getNguoiDung(maNguoiDung);
        KhoaHoc khoaHoc = getKhoaHoc(maKhoaHoc);
        BaiHoc baiHoc = getBaiHoc(maBaiHoc);

        TienDo tienDo = findOrCreateTienDo(nguoiDung, khoaHoc, baiHoc);
        updateTienDoDetails(tienDo, phanTram, thoiGianHoc);

        return tienDoRepository.save(tienDo);
    }

    public List<TienDo> findByNguoiDung(Integer maNguoiDung) {
        if (maNguoiDung == null) {
            throw new IllegalArgumentException("Mã người dùng không được để trống");
        }
        return tienDoRepository.findByNguoiDungMaNguoiDung(maNguoiDung);
    }

    public Optional<TienDo> findByNguoiDungAndKhoaHocAndBaiHoc(Integer maNguoiDung, Integer maKhoaHoc, Integer maBaiHoc) {
        if (maNguoiDung == null || maKhoaHoc == null || maBaiHoc == null) {
            throw new IllegalArgumentException("Mã người dùng, khóa học hoặc bài học không được để trống");
        }
        NguoiDung nguoiDung = getNguoiDung(maNguoiDung);
        KhoaHoc khoaHoc = getKhoaHoc(maKhoaHoc);
        BaiHoc baiHoc = getBaiHoc(maBaiHoc);
        return tienDoRepository.findByNguoiDungAndKhoaHocAndBaiHoc(nguoiDung, khoaHoc, baiHoc);
    }

    private NguoiDung getNguoiDung(Integer maNguoiDung) {
        return nguoiDungRepository.findById(maNguoiDung)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại"));
    }

    private KhoaHoc getKhoaHoc(Integer maKhoaHoc) {
        return khoaHocRepository.findById(maKhoaHoc)
                .orElseThrow(() -> new IllegalArgumentException("Khóa học không tồn tại"));
    }

    private BaiHoc getBaiHoc(Integer maBaiHoc) {
        return baiHocRepository.findById(maBaiHoc)
                .orElseThrow(() -> new IllegalArgumentException("Bài học không tồn tại"));
    }

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

    private void updateTienDoDetails(TienDo tienDo, BigDecimal phanTram, Integer thoiGianHoc) {
        tienDo.setPhanTram(phanTram);
        tienDo.setThoiGianHoc(thoiGianHoc);
        tienDo.setHoanThanh(phanTram.compareTo(BigDecimal.valueOf(100)) >= 0);
        tienDo.setLanCuoiHoc(LocalDateTime.now());
    }

    public TienDo findByKhoaHocAndNguoiDung(Integer khoaHocId, Integer nguoiDungId) {
        NguoiDung nguoiDung = getNguoiDung(nguoiDungId);
        KhoaHoc khoaHoc = getKhoaHoc(khoaHocId);
        List<BaiHoc> baiHocList = baiHocRepository.findByKhoaHoc_MaKhoaHoc(khoaHocId);

        if (baiHocList.isEmpty()) {
            throw new IllegalArgumentException("Khóa học không có bài học");
        }

        BaiHoc baiHoc = baiHocList.get(0);
        return tienDoRepository.findByNguoiDungAndKhoaHocAndBaiHoc(nguoiDung, khoaHoc, baiHoc)
                .orElse(new TienDo(nguoiDung, khoaHoc, baiHoc));
    }

    public Map<Integer, TienDo> getAllByKhoaHocAndNguoiDungAsMap(Integer khoaHocId, Integer nguoiDungId) {
        KhoaHoc khoaHoc = getKhoaHoc(khoaHocId);
        NguoiDung nguoiDung = getNguoiDung(nguoiDungId);

        List<TienDo> tienDoList = tienDoRepository.findByKhoaHocAndNguoiDung(khoaHoc, nguoiDung);

        return tienDoList.stream()
                .filter(td -> td.getBaiHoc() != null && td.getBaiHoc().getMaBaiHoc() != null)
                .collect(Collectors.toMap(td -> td.getBaiHoc().getMaBaiHoc(), td -> td));
    }

    public List<UserCourseProgressDTO> getUserCourseProgress(Integer maNguoiDung) {
        List<TienDo> tienDoList = tienDoRepository.findByNguoiDungMaNguoiDung(maNguoiDung);

        Map<KhoaHoc, List<TienDo>> progressByCourse = tienDoList.stream()
                .filter(td -> td.getKhoaHoc() != null && td.getKhoaHoc().getMaKhoaHoc() != null)
                .collect(Collectors.groupingBy(TienDo::getKhoaHoc));

        return progressByCourse.entrySet().stream()
                .map(entry -> {
                    KhoaHoc khoaHoc = entry.getKey();
                    List<TienDo> progressList = entry.getValue();

                    long completedLessons = progressList.stream().filter(TienDo::getHoanThanh).count();
                    long totalLessons = baiHocRepository.countByKhoaHoc_MaKhoaHoc(khoaHoc.getMaKhoaHoc());

                    BigDecimal percent = BigDecimal.ZERO;
                    if (totalLessons > 0) {
                        percent = BigDecimal.valueOf(completedLessons)
                                .divide(BigDecimal.valueOf(totalLessons), 2, BigDecimal.ROUND_HALF_UP)
                                .multiply(BigDecimal.valueOf(100));
                    }

                    return new UserCourseProgressDTO(
                            khoaHoc.getMaKhoaHoc(),
                            khoaHoc.getTenKhoaHoc(),
                            khoaHoc.getGiangVien() != null ? khoaHoc.getGiangVien().getHoTen() : "Không rõ",
                            percent,
                            completedLessons,
                            totalLessons,
                            khoaHoc.getHinhAnh() != null ? khoaHoc.getHinhAnh() : "/images/default-course.jpg"
                    );
                })
                .filter(dto -> dto.getMaKhoaHoc() != null)
                .collect(Collectors.toList());
    }

    public long countCoursesInProgress(Integer maNguoiDung) {
        return tienDoRepository.findByNguoiDungMaNguoiDung(maNguoiDung).stream()
                .map(TienDo::getKhoaHoc)
                .filter(Objects::nonNull)
                .map(KhoaHoc::getMaKhoaHoc)
                .filter(Objects::nonNull)
                .distinct()
                .count();
    }

    public long calculateTotalStudyHours(Integer maNguoiDung) {
        return tienDoRepository.findByNguoiDungMaNguoiDung(maNguoiDung).stream()
                .mapToLong(td -> Optional.ofNullable(td.getThoiGianHoc()).orElse(0))
                .sum() / 3600;
    }

    public long countCompletedLessons(Integer maNguoiDung) {
        return tienDoRepository.findByNguoiDungMaNguoiDung(maNguoiDung).stream()
                .filter(TienDo::getHoanThanh)
                .count();
    }
}
