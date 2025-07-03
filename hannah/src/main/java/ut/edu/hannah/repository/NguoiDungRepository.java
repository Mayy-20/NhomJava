package ut.edu.hannah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ut.edu.hannah.model.NguoiDung;

import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    /**
     * Tìm người dùng theo tên đăng nhập.
     * @param tenDangNhap Tên đăng nhập.
     * @return Optional chứa người dùng (nếu có).
     */
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);

    /**
     * Tìm người dùng theo email.
     * @param email Email.
     * @return Optional chứa người dùng (nếu có).
     */
    Optional<NguoiDung> findByEmail(String email);
}