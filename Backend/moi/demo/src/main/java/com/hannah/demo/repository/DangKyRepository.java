package com.hannah.demo.repository;

import com.hannah.demo.model.DangKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangKyRepository extends JpaRepository<DangKy, Integer> {
}
