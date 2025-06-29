package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.hannah.model.TaiLieu;

import java.util.List;

public interface TaiLieuRepository extends JpaRepository<TaiLieu, Integer> {
    List<TaiLieu> findByBaiHocMaBaiHoc(Integer maBaiHoc);
}