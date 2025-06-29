package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.hannah.model.BinhLuan;

import java.util.List;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, Integer> {
    List<BinhLuan> findByMaKhoaHoc(Integer maKhoaHoc);
}