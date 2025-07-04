package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.BaiHoc;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaiHocRepository extends JpaRepository<BaiHoc, Integer> {
    List<BaiHoc> findByKhoaHoc_MaKhoaHoc(Integer maKhoaHoc);
    Optional<BaiHoc> findFirstByKhoaHoc_MaKhoaHocOrderByThuTuAsc(Integer maKhoaHoc);

}