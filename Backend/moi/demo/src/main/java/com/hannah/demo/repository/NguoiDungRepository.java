package com.hannah.demo.repository;

import com.hannah.demo.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    List<NguoiDung> findByTrangThai(NguoiDung.TrangThai trangThai);
    List<NguoiDung> findByMaVaiTro_MaVaiTro(Integer maVaiTro);
}