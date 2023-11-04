package kim.jiwook.playground.mapper;

import kim.jiwook.playground.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BoardMapper {

    int insertBoard(BoardVO vo) throws IOException, SQLException;
    List<BoardVO> selectAllBoard() throws IOException, SQLException;
    BoardVO selectBoardBySeq(BoardVO vo) throws IOException, SQLException;

}
