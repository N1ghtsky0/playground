package com.jiwook.playground.repository;

import com.jiwook.playground.entity.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepo extends JpaRepository<MainEntity, Long> {
    MainEntity findBySeq(Long seq);
}
