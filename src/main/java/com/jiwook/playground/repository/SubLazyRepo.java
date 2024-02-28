package com.jiwook.playground.repository;

import com.jiwook.playground.entity.SubLazyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubLazyRepo extends JpaRepository<SubLazyEntity, Long> {
    SubLazyEntity findBySeq(Long seq);
}
