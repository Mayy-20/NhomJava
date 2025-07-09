package com.hannah.demo.repository;

import com.hannah.demo.model.TaiLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiLieuRepository extends JpaRepository<TaiLieu, Integer> {
    List<TaiLieu> findByTrangThai(TaiLieu.TrangThai trangThai);
    List<TaiLieu> findByMaLoaiTaiLieu_MaLoaiTaiLieu(Integer maLoaiTaiLieu);
}