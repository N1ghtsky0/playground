package jiwook.kim.playground.repository;

import jiwook.kim.playground.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board, Long> {
}
