package com.jiwook.playground.repository;

import com.jiwook.playground.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, String> {
    RefreshToken findByUuid(String uuid);
}
