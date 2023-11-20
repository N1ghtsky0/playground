package kim.jiwook.playground.repository;

import kim.jiwook.playground.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepo extends JpaRepository<Board, Long> {

    Optional<Board> findBoardBySeq(long seq);
}
