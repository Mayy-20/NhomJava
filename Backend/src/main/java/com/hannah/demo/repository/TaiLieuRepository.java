package com.hannah.demo.repository;

import com.hannah.demo.model.TaiLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiLieuRepository extends JpaRepository<TaiLieu, Integer> {
    
    List<TaiLieu> findByTrangThai(TaiLieu.TrangThaiTaiLieu trangThai);
    
    List<TaiLieu> findByMaTacGia(Integer maTacGia);
    
    List<TaiLieu> findByMaBaiHoc(Integer maBaiHoc);
    
    @Query("SELECT t FROM TaiLieu t WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(t.tenTaiLieu) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
           "(:trangThai IS NULL OR t.trangThai = :trangThai) AND " +
           "(:maTacGia IS NULL OR t.maTacGia = :maTacGia)")
    Page<TaiLieu> findWithFilters(@Param("search") String search,
                                 @Param("trangThai") TaiLieu.TrangThaiTaiLieu trangThai,
                                 @Param("maTacGia") Integer maTacGia,
                                 Pageable pageable);
    
    @Query("SELECT COUNT(t) FROM TaiLieu t WHERE t.trangThai = :trangThai")
    Long countByTrangThai(@Param("trangThai") TaiLieu.TrangThaiTaiLieu trangThai);
}
