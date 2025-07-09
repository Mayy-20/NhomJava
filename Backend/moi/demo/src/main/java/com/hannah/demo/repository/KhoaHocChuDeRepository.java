package com.hannah.demo.repository;

import com.hannah.demo.model.KhoaHocChuDe;
import com.hannah.demo.model.KhoaHocChuDeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoaHocChuDeRepository extends JpaRepository<KhoaHocChuDe, KhoaHocChuDeId> {
}