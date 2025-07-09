package com.hannah.demo.repository;

import com.hannah.demo.model.BaiDang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiDangRepository extends JpaRepository<BaiDang, Integer>, JpaSpecificationExecutor<BaiDang> {
    List<BaiDang> findByTrangThai(BaiDang.TrangThai trangThai);
    List<BaiDang> findByMaChuDe_MaChuDe(Integer maChuDe);
}