package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.hannah.model.TienDo;

import java.util.List;

public interface TienDoRepository extends JpaRepository<TienDo, Integer> {
    List<TienDo> findByMaNguoiDung(Integer maNguoiDung);
    List<TienDo> findByMaNguoiDungAndMaKhoaHoc(Integer maNguoiDung, Integer maKhoaHoc);
}