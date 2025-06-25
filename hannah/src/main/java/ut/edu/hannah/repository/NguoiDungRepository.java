package ut.edu.hannah.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ut.edu.hannah.model.NguoiDung;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
    Optional<NguoiDung> findByEmail(String email);
}