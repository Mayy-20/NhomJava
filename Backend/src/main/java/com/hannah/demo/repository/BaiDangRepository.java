package com.hannah.demo.repository;

import com.hannah.demo.model.BaiDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiDangRepository extends JpaRepository<BaiDang, Integer> {
    
    List<BaiDang> findByTrangThai(BaiDang.TrangThaiBaiDang trangThai);
    
    List<BaiDang> findByMaTacGia(Integer maTacGia);
    
    List<BaiDang> findBySoBaoCaoGreaterThan(Integer soBaoCao);
    
    @Query("SELECT b FROM BaiDang b WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(b.tieuDe) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
           "(:trangThai IS NULL OR b.trangThai = :trangThai) AND " +
           "(:maChuDe IS NULL OR b.maChuDe = :maChuDe)")
    Page<BaiDang> findWithFilters(@Param("search") String search,
                                 @Param("trangThai") BaiDang.TrangThaiBaiDang trangThai,
                                 @Param("maChuDe") Integer maChuDe,
                                 Pageable pageable);
    
    @Query("SELECT COUNT(b) FROM BaiDang b WHERE b.trangThai = :trangThai")
    Long countByTrangThai(@Param("trangThai") BaiDang.TrangThaiBaiDang trangThai);
}
