package ut.nhomjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Đảm bảo import đúng model

import ut.nhomjava.model.VaiTro;

@Repository
public interface VaiTroRepository extends JpaRepository<VaiTro, Integer> {
    Optional<VaiTro> findByTenVaiTro(String tenVaiTro); // Phương thức để tìm vai trò theo tên
}