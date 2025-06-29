package com.hannah.demo.repository;

import com.hannah.demo.model.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    
    Optional<NguoiDung> findByTenDangNhap(String tenDangNhap);
    
    Optional<NguoiDung> findByEmail(String email);
    
    boolean existsByTenDangNhap(String tenDangNhap);
    
    boolean existsByEmail(String email);
    
    List<NguoiDung> findByTrangThai(NguoiDung.TrangThaiNguoiDung trangThai);
    
    List<NguoiDung> findByMaVaiTro(Integer maVaiTro);
    
    @Query("SELECT n FROM NguoiDung n WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(n.hoTen) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(n.email) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
           "(:trangThai IS NULL OR n.trangThai = :trangThai) AND " +
           "(:maVaiTro IS NULL OR n.maVaiTro = :maVaiTro)")
    Page<NguoiDung> findWithFilters(@Param("search") String search,
                                   @Param("trangThai") NguoiDung.TrangThaiNguoiDung trangThai,
                                   @Param("maVaiTro") Integer maVaiTro,
                                   Pageable pageable);
    
    @Query("SELECT COUNT(n) FROM NguoiDung n WHERE n.trangThai = :trangThai")
    Long countByTrangThai(@Param("trangThai") NguoiDung.TrangThaiNguoiDung trangThai);
}
