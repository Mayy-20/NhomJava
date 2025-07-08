package ut.nhomjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ut.nhomjava.model.BinhLuan; // Import List

@Repository
public interface BinhLuanRepository extends JpaRepository<BinhLuan, Integer> {
    // Thêm phương thức này để tìm bình luận theo Mã Bài Đăng
    List<BinhLuan> findByBaiDang_MaBaiDang(Integer maBaiDang);
}