package com.jiwook.playground.repository;

import com.jiwook.playground.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Boolean existsByLoginId(String loginId);

    Optional<User> findByLoginId(String loginId);
}
