package com.jiwook.playground.repository;

import com.jiwook.playground.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
