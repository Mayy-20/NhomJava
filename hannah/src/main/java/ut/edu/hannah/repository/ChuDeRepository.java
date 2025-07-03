package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.ChuDe;

@Repository
public interface ChuDeRepository extends JpaRepository<ChuDe, Integer> {
    ChuDe findByTenChuDe(String tenChuDe);
}