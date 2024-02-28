package com.jiwook.playground.repository;

import com.jiwook.playground.entity.SubEagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubEagerRepo extends JpaRepository<SubEagerEntity, Long> {
    SubEagerEntity findBySeq(Long seq);
}
