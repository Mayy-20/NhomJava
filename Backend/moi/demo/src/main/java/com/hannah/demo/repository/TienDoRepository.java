package com.hannah.demo.repository;

import com.hannah.demo.model.TienDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TienDoRepository extends JpaRepository<TienDo, Integer> {
}