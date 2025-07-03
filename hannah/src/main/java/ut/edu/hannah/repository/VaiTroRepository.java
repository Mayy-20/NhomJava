package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.VaiTro;

import java.util.Optional;

/**
 * Repository để thao tác với bảng vaitro trong cơ sở dữ liệu.
 */
@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {
    /**
     * Tìm vai trò theo mã vai trò.
     * @param maVaiTro Mã vai trò.
     * @return Optional chứa vai trò (nếu có).
     */
    Optional<VaiTro> findById(Integer maVaiTro);
}