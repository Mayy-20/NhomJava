package ut.edu.hannah.services;
import org.springframework.stereotype.Service;
import ut.edu.hannah.model.BaiHoc;
import ut.edu.hannah.model.KhoaHoc;
import ut.edu.hannah.model.TienDo;
import ut.edu.hannah.repository.BaiHocRepository;
import ut.edu.hannah.repository.KhoaHocRepository;
import ut.edu.hannah.repository.TienDoRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class TienDoService {

    private final TienDoRepository tienDoRepository;
    private final KhoaHocRepository khoaHocRepository;
    private final BaiHocRepository baiHocRepository;

    public TienDoService(
        TienDoRepository tienDoRepository,
        KhoaHocRepository khoaHocRepository,
        BaiHocRepository baiHocRepository
    ) {
        this.tienDoRepository = tienDoRepository;
        this.khoaHocRepository = khoaHocRepository;
        this.baiHocRepository = baiHocRepository;
    }

    public List<Object[]> getUserProgress(Integer maNguoiDung) {
        List<TienDo> tienDoList = tienDoRepository.findByMaNguoiDung(maNguoiDung);
        List<Object[]> progressData = new ArrayList<>();

        for (TienDo tienDo : tienDoList) {
            KhoaHoc khoaHoc = khoaHocRepository.findById(tienDo.getMaKhoaHoc()).orElse(null);
            List<BaiHoc> baiHocList = baiHocRepository.findByMaKhoaHoc(tienDo.getMaKhoaHoc());
            int totalLessons = baiHocList.size();
            int completedLessons = tienDoRepository.findByMaNguoiDungAndMaKhoaHoc(maNguoiDung, tienDo.getMaKhoaHoc())
                    .stream()
                    .filter(t -> t.getHoanThanh() != null && t.getHoanThanh())
                    .mapToInt(t -> 1)
                    .sum();
            if (khoaHoc != null) {
                progressData.add(new Object[]{khoaHoc.getTenKhoaHoc(), tienDo.getPhanTram(), completedLessons, totalLessons});
            }
        }
        return progressData;
    }

    public int getTotalCourses(Integer maNguoiDung) {
        return tienDoRepository.findByMaNguoiDung(maNguoiDung)
                .stream()
                .map(TienDo::getMaKhoaHoc)
                .distinct()
                .mapToInt(i -> 1)
                .sum();
    }

    public int getTotalHours(Integer maNguoiDung) {
        return tienDoRepository.findByMaNguoiDung(maNguoiDung)
                .stream()
                .mapToInt(t -> t.getThoiGianHoc() != null ? t.getThoiGianHoc() : 0)
                .sum();
    }

    public int getCompletedLessons(Integer maNguoiDung) {
        return tienDoRepository.findByMaNguoiDung(maNguoiDung)
                .stream()
                .filter(t -> t.getHoanThanh() != null && t.getHoanThanh())
                .mapToInt(t -> 1)
                .sum();
    }
}