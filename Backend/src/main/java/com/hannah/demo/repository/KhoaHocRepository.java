package com.hannah.demo.repository;

import com.hannah.demo.model.KhoaHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer> {
    
    List<KhoaHoc> findByTrangThai(KhoaHoc.TrangThaiKhoaHoc trangThai);
    
    List<KhoaHoc> findByMaGiangVien(Integer maGiangVien);
    
    @Query("SELECT k FROM KhoaHoc k WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(k.tenKhoaHoc) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
           "(:trangThai IS NULL OR k.trangThai = :trangThai) AND " +
           "(:maGiangVien IS NULL OR k.maGiangVien = :maGiangVien)")
    Page<KhoaHoc> findWithFilters(@Param("search") String search,
                                 @Param("trangThai") KhoaHoc.TrangThaiKhoaHoc trangThai,
                                 @Param("maGiangVien") Integer maGiangVien,
                                 Pageable pageable);
    
    @Query("SELECT COUNT(k) FROM KhoaHoc k WHERE k.trangThai = :trangThai")
    Long countByTrangThai(@Param("trangThai") KhoaHoc.TrangThaiKhoaHoc trangThai);
}
