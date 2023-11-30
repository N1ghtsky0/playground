package jiwook.kim.playground.repository;

import jiwook.kim.playground.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {

    boolean existsAccountByLoginId(String loginId);

    boolean existsAccountByNickName(String nickName);

}
