package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.hannah.model.BaiHoc;

import java.util.List;

public interface BaiHocRepository extends JpaRepository<BaiHoc, Integer> {
    List<BaiHoc> findByKhoaHocMaKhoaHoc(Integer maKhoaHoc);
    BaiHoc findByKhoaHocMaKhoaHocAndThuTu(Integer maKhoaHoc, Integer thuTu);
}