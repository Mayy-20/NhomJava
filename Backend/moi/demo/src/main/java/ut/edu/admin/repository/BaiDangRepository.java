package ut.edu.admin.repository;

import ut.edu.admin.model.BaiDang;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BaiDangRepository extends JpaRepository<BaiDang, Integer> {
    List<BaiDang> findByTrangThai(BaiDang.TrangThaiBaiDang trangThai);
}
