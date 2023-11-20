package kim.jiwook.playground.repository;

import kim.jiwook.playground.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board, Long> {
}
