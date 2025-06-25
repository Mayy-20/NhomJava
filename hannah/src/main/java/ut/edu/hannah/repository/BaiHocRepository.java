package ut.edu.hannah.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.hannah.model.BaiHoc;

public interface BaiHocRepository extends JpaRepository<BaiHoc, Integer> {
    List<BaiHoc> findByMaKhoaHoc(Integer maKhoaHoc);
}