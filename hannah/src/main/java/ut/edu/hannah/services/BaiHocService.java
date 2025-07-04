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


}