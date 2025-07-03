package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.DangKy;
import ut.edu.hannah.model.NguoiDung;
import ut.edu.hannah.model.KhoaHoc;

import java.util.List;

/**
 * Repository để quản lý các thao tác truy vấn với bảng `dangky`.
 * Kế thừa từ JpaRepository để có các phương thức CRUD cơ bản.
 */
@Repository
public interface DangKyRepository extends JpaRepository<DangKy, Integer> {
    List<DangKy> findByNguoiDungMaNguoiDung(Integer maNguoiDung);
    List<DangKy> findByKhoaHocMaKhoaHoc(Integer maKhoaHoc);
    boolean existsByNguoiDungAndKhoaHoc(NguoiDung nguoiDung, KhoaHoc khoaHoc);
}