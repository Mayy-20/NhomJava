package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.TienDo;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.model.KhoaHoc;
import ut.edu.hannah.model.BaiHoc;

import java.util.List;
import java.util.Optional;

@Repository
public interface TienDoRepository extends JpaRepository<TienDo, Integer> {
    List<TienDo> findByNguoiDungMaNguoiDung(Integer maNguoiDung);
    Optional<TienDo> findByNguoiDungAndKhoaHocAndBaiHoc(NguoiDung nguoiDung, KhoaHoc khoaHoc, BaiHoc baiHoc);
}