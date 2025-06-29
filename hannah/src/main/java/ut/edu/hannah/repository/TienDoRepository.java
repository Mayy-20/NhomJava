package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.hannah.model.TienDo;

import java.util.List;

public interface TienDoRepository extends JpaRepository<TienDo, Integer> {
    List<TienDo> findByNguoiDungMaNguoiDungAndKhoaHocMaKhoaHoc(Integer maNguoiDung, Integer maKhoaHoc);
}