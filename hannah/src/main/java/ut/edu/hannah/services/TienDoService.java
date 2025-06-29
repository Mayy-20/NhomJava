package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.TienDo;
import ut.edu.hannah.repository.BaiHocRepository;
import ut.edu.hannah.repository.TienDoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TienDoService {

    private final TienDoRepository tienDoRepository;
    private final BaiHocRepository baiHocRepository;

    public TienDoService(TienDoRepository tienDoRepository, BaiHocRepository baiHocRepository) {
        this.tienDoRepository = tienDoRepository;
        this.baiHocRepository = baiHocRepository;
    }

    public List<ProgressDTO> getProgressByCourse(Integer maNguoiDung, Integer maKhoaHoc) {
        List<TienDo> progresses = tienDoRepository.findByNguoiDungMaNguoiDungAndKhoaHocMaKhoaHoc(maNguoiDung, maKhoaHoc);
        List<ProgressDTO> result = new ArrayList<>();
        if (progresses == null || progresses.isEmpty()) {
            return result;
        }

        int totalLessons = baiHocRepository.findByKhoaHocMaKhoaHoc(maKhoaHoc).size();
        int completedLessons = 0;
        int totalHours = 0;

        for (TienDo tienDo : progresses) {
            if (tienDo.getTrangThai() == TienDo.TrangThai.HOAN_THANH) {
                completedLessons++;
            }
            totalHours += Integer.parseInt(tienDo.getBaiHoc().getThoiLuong());
        }

        int phanTram = totalLessons > 0 ? (completedLessons * 100 / totalLessons) : 0;
        ProgressDTO progressDTO = new ProgressDTO(totalLessons, completedLessons, phanTram, totalHours);
        result.add(progressDTO);
        return result;
    }

    public int getTotalCourses(Integer maNguoiDung) {
        List<TienDo> progresses = tienDoRepository.findAll();
        return (int) progresses.stream()
                .filter(t -> t.getNguoiDung().getMaNguoiDung().equals(maNguoiDung))
                .map(TienDo::getKhoaHoc)
                .distinct()
                .count();
    }

    public int getTotalHours(Integer maNguoiDung) {
        List<TienDo> progresses = tienDoRepository.findAll();
        return progresses.stream()
                .filter(t -> t.getNguoiDung().getMaNguoiDung().equals(maNguoiDung))
                .mapToInt(t -> Integer.parseInt(t.getBaiHoc().getThoiLuong()))
                .sum();
    }

    public int getCompletedLessons(Integer maNguoiDung) {
        List<TienDo> progresses = tienDoRepository.findAll();
        return (int) progresses.stream()
                .filter(t -> t.getNguoiDung().getMaNguoiDung().equals(maNguoiDung))
                .filter(t -> t.getTrangThai() == TienDo.TrangThai.HOAN_THANH)
                .count();
    }

    public static class ProgressDTO {
        private final int totalLessons;
        private final int completedLessons;
        private final int phanTram;
        private final int totalHours;

        public ProgressDTO(int totalLessons, int completedLessons, int phanTram, int totalHours) {
            this.totalLessons = totalLessons;
            this.completedLessons = completedLessons;
            this.phanTram = phanTram;
            this.totalHours = totalHours;
        }

        public int getTotalLessons() {
            return totalLessons;
        }

        public int getCompletedLessons() {
            return completedLessons;
        }

        public int getPhanTram() {
            return phanTram;
        }

        public int getTotalHours() {
            return totalHours;
        }
    }
}