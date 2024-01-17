package com.jiwook.playground.repository;

import com.jiwook.playground.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    Boolean existsByLoginId(String loginId);
}
