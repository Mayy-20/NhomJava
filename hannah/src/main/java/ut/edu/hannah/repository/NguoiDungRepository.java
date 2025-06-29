package ut.edu.hannah.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ut.edu.hannah.model.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
   NguoiDung findByTenDangNhap(String tenDangNhap);
   NguoiDung findByMaNguoiDung(Integer maNguoiDung);
   NguoiDung findByEmail(String email);
}