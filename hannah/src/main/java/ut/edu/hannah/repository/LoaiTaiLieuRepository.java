package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.LoaiTaiLieu;

/**
 * Repository để quản lý các thao tác truy vấn với bảng `loaitailieu`.
 * Kế thừa từ JpaRepository để có các phương thức CRUD cơ bản.
 */
@Repository
public interface LoaiTaiLieuRepository extends JpaRepository<LoaiTaiLieu, Integer> {
    /**
     * Tìm loại tài liệu theo tên.
     *
     * @param tenLoai Tên loại tài liệu.
     * @return Loại tài liệu tương ứng, hoặc null nếu không tìm thấy.
     */
    LoaiTaiLieu findByTenLoai(String tenLoai);
}