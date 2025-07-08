package ut.nhomjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ut.nhomjava.model.KhoaHoc;

@Repository
public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer> {
    // Có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần, ví dụ:
    // List<KhoaHoc> findByGiangVien_MaNguoiDung(Integer maGiangVien);
    // List<KhoaHoc> findByTenKhoaHocContainingIgnoreCase(String tenKhoaHoc);
}