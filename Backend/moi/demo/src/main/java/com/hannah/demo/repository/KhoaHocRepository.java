package com.hannah.demo.repository;

import com.hannah.demo.model.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Integer> {
    List<KhoaHoc> findByTrangThai(KhoaHoc.TrangThai trangThai);
}