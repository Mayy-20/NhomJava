package com.hannah.demo.repository;

import com.hannah.demo.model.PhienChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhienChatRepository extends JpaRepository<PhienChat, Integer> {
}