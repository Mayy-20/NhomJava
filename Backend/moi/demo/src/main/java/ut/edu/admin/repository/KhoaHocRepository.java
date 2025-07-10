package ut.edu.admin.repository;

import ut.edu.admin.model.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer> {
    List<KhoaHoc> findByTrangThai(KhoaHoc.TrangThaiKhoaHoc trangThai);
}
