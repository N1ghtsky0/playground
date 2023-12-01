package jiwook.kim.playground.repository;

import jiwook.kim.playground.Entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findRefreshTokenByAccessToken(String accessToken);

    Optional<RefreshToken> findRefreshTokenById(String uuid);
}