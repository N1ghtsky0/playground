package kim.jiwook.playground.service.impl;

import kim.jiwook.playground.mapper.BoardMapper;
import kim.jiwook.playground.service.BoardService;
import kim.jiwook.playground.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public int insertBoard(BoardVO vo) throws IOException, SQLException {
        return boardMapper.insertBoard(vo);
    }

    @Override
    public List<BoardVO> selectAllBoard() throws IOException, SQLException {
        return boardMapper.selectAllBoard();
    }

    @Override
    public BoardVO selectBoardBySeq(long seq) throws IOException, SQLException {
        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        return boardMapper.selectBoardBySeq(vo);
    }

}
