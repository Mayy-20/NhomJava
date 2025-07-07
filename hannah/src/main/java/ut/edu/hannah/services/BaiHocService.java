package ut.edu.hannah.services;

import org.springframework.stereotype.Service;
import ut.edu.hannah.model.BaiHoc;
import ut.edu.hannah.repository.BaiHocRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service để quản lý các thao tác liên quan đến bài học.
 */
@Service
public class BaiHocService {
    private final BaiHocRepository baiHocRepository;

    public BaiHocService(BaiHocRepository baiHocRepository) {
        this.baiHocRepository = baiHocRepository;
    }

    public List<BaiHoc> getAllBaiHoc() {
        return baiHocRepository.findAll();
    }

    public List<BaiHoc> findByKhoaHoc(Integer maKhoaHoc) {
        if (maKhoaHoc == null) {
            throw new IllegalArgumentException("Mã khóa học không được để trống");
        }
        List<BaiHoc> lessons = baiHocRepository.findByKhoaHoc_MaKhoaHoc(maKhoaHoc);
        System.out.println("==> Found " + lessons.size() + " bài học cho khóa học ID = " + maKhoaHoc);
        return lessons;
    }

    public Optional<BaiHoc> findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Mã bài học không được để trống");
        }
        return baiHocRepository.findById(id);
    }

    public Optional<BaiHoc> findFirstLessonByCourseId(Integer maKhoaHoc) {
        return baiHocRepository.findFirstByKhoaHoc_MaKhoaHocOrderByThuTuAsc(maKhoaHoc);
    }

    /**
     * Tính tổng thời lượng của tất cả các bài học trong một khóa học.
     * Thời lượng được lưu trong DB là phút, phương thức này sẽ trả về định dạng "X giờ Y phút".
     *
     * @param maKhoaHoc Mã khóa học.
     * @return Chuỗi định dạng thời lượng.
     */
    public String calculateTotalDuration(Integer maKhoaHoc) {
        List<BaiHoc> lessons = findByKhoaHoc(maKhoaHoc);
        int totalMinutes = 0;
        for (BaiHoc lesson : lessons) {
            try {
                if (lesson.getThoiLuong() != null && !lesson.getThoiLuong().isEmpty()) {
                    totalMinutes += Integer.parseInt(lesson.getThoiLuong());
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi chuyển đổi thời lượng bài học '" + lesson.getThoiLuong() + "' thành số: " + e.getMessage());
            }
        }

        if (totalMinutes == 0) {
            return "0 phút";
        }

        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        if (hours > 0 && minutes > 0) {
            return hours + " giờ " + minutes + " phút";
        } else if (hours > 0) {
            return hours + " giờ";
        } else {
            return minutes + " phút";
        }
    }
}