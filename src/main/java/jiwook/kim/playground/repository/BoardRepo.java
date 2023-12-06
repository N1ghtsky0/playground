package jiwook.kim.playground.repository;

import jiwook.kim.playground.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepo extends JpaRepository<Board, Long> {
    Optional<Board> findBoardBySeq(Long seq);

    @Query("select b from Board b order by b.createDate desc")
    List<Board> findAllDesc();
}
