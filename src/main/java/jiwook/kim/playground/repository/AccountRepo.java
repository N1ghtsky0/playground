package jiwook.kim.playground.repository;

import jiwook.kim.playground.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {

    boolean existsAccountByLoginId(String loginId);

    boolean existsAccountByNickName(String nickName);

    Optional<Account> findAccountByLoginId(String loginId);

    Optional<Account> findAccountByUuid(String uuid);

    Optional<Account> findAccountByNickNameAndUuid(String nickName, String uuid);

}
