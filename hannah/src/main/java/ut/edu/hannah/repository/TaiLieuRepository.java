package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.TaiLieu;

import java.util.List;

@Repository
public interface TaiLieuRepository extends JpaRepository<TaiLieu, Integer> {
    List<TaiLieu> findByTrangThai(TaiLieu.TrangThai trangThai);
    List<TaiLieu> findByBaiHocMaBaiHoc(Integer maBaiHoc);
    List<TaiLieu> findByTacGiaMaNguoiDung(Integer maTacGia);
}