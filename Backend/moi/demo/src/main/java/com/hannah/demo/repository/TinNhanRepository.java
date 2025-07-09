package com.hannah.demo.repository;

import com.hannah.demo.model.TinNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinNhanRepository extends JpaRepository<TinNhan, Integer> {
}