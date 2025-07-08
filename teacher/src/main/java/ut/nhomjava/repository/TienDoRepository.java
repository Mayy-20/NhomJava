package ut.nhomjava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ut.nhomjava.model.KhoaHoc;
import ut.nhomjava.model.NguoiDung;
import ut.nhomjava.model.TienDo;

@Repository
public interface TienDoRepository extends JpaRepository<TienDo, Integer> {
    Optional<TienDo> findByNguoiDungAndKhoaHoc(NguoiDung nguoiDung, KhoaHoc khoaHoc);
    List<TienDo> findByNguoiDung_MaNguoiDung(Integer maNguoiDung);
    List<TienDo> findByKhoaHoc_MaKhoaHoc(Integer maKhoaHoc);
}