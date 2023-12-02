package jiwook.kim.playground.repository;

import jiwook.kim.playground.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepo extends JpaRepository<Board, Long> {
    Optional<Board> findBoardBySeq(Long seq);
}
