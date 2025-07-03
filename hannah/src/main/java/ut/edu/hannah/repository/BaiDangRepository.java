package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.BaiDang;
import ut.edu.hannah.model.ChuDe;

import java.util.List;

/**
 * Repository để quản lý các thao tác truy vấn với bảng `baidang`.
 * Kế thừa từ JpaRepository để có các phương thức CRUD cơ bản.
 */
@Repository
public interface BaiDangRepository extends JpaRepository<BaiDang, Integer> {
    
    List<BaiDang> findByTacGia_MaNguoiDung(Integer maTacGia);
    List<BaiDang> findByChuDe(ChuDe chuDe);
    List<BaiDang> findByBaiHoc_MaBaiHoc(Integer maBaiHoc);
    List<BaiDang> findByBaiHocKhoaHocMaKhoaHoc(Integer maKhoaHoc);
    List<BaiDang> findByTrangThai(BaiDang.TrangThai trangThai);
}