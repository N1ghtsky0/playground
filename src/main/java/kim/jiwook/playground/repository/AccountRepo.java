package kim.jiwook.playground.repository;

import kim.jiwook.playground.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByLoginId(String loginId);

    Optional<Account> findAccountByUuid(String uuid);
}
