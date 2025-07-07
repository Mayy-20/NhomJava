package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.VaiTro;


/**
 * Repository để thao tác với bảng vaitro trong cơ sở dữ liệu.
 */
@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {
}