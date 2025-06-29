package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.hannah.model.KhoaHoc;

import java.util.List;

public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer> {
    List<KhoaHoc> findByTrangThai(KhoaHoc.TrangThai trangThai);
}