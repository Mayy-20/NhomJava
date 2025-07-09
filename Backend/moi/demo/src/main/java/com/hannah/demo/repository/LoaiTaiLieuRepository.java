package com.hannah.demo.repository;

import com.hannah.demo.model.LoaiTaiLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiTaiLieuRepository extends JpaRepository<LoaiTaiLieu, Integer> {
}