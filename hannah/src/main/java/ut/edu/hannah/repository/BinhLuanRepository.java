package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.BinhLuan;
import ut.edu.hannah.model.BaiDang;
import java.util.List;

/**
 * Repository để quản lý các thao tác truy vấn với bảng `binhluan`.
 * Kế thừa từ JpaRepository để có các phương thức CRUD cơ bản.
 */
@Repository
public interface BinhLuanRepository extends JpaRepository<BinhLuan, Integer> {
    /**
     * Tìm danh sách bình luận theo danh sách bài đăng.
     * @param baiDangList Danh sách bài đăng.
     * @return Danh sách bình luận liên quan.
     */
    List<BinhLuan> findByBaiDangIn(List<BaiDang> baiDangList);
}