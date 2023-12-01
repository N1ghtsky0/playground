package jiwook.kim.playground.repository;

import jiwook.kim.playground.Entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepo extends CrudRepository<RefreshToken, String> {

}