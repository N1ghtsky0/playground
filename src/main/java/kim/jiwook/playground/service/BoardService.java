package kim.jiwook.playground.service;

import kim.jiwook.playground.vo.BoardVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    int insertBoard(BoardVO vo) throws IOException, SQLException;
    List<BoardVO> selectAllBoard() throws IOException, SQLException;
    BoardVO selectBoardBySeq(long seq) throws IOException, SQLException;

}
