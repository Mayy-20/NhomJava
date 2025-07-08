package ut.nhomjava.repository; // Đảm bảo đúng package repository của bạn

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.nhomjava.model.BaiDang; // Import Entity BaiDang

@Repository
public interface BaiDangRepository extends JpaRepository<BaiDang, Integer> {
    // Tham số thứ nhất là tên Entity (BaiDang)
    // Tham số thứ hai là kiểu dữ liệu của trường @Id trong Entity đó (Integer)

    // Các phương thức tùy chỉnh nếu có
}