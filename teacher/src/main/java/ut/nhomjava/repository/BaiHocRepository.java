package ut.nhomjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ut.nhomjava.model.BaiHoc;

@Repository
public interface BaiHocRepository extends JpaRepository<BaiHoc, Integer> {
    List<BaiHoc> findByKhoaHoc_MaKhoaHocOrderByThuTuAsc(Integer maKhoaHoc);
}