package kim.jiwook.playground.service.impl;

import kim.jiwook.playground.mapper.BoardMapper;
import kim.jiwook.playground.service.BoardService;
import kim.jiwook.playground.vo.BoardVO;
import kim.jiwook.playground.vo.request.RequestInsertBoard;
import kim.jiwook.playground.vo.response.ResponseSelectAllBoard;
import kim.jiwook.playground.vo.response.ResponseSelectBoardBySeq;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final ModelMapper modelMapper;

    @Override
    public int insertBoard(RequestInsertBoard vo) throws IOException, SQLException {
        if (vo.getAuthor() == null || vo.getAuthor().isEmpty()) {
            vo.setAuthor("anonymous");
        }
        BoardVO boardVO = modelMapper.map(vo, BoardVO.class);
        return boardMapper.insertBoard(boardVO);
    }

    @Override
    public List<ResponseSelectAllBoard> selectAllBoard() throws IOException, SQLException {
        return boardMapper.selectAllBoard()
                .stream()
                .map(boardVO -> modelMapper.map(boardVO, ResponseSelectAllBoard.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseSelectBoardBySeq selectBoardBySeq(long seq) throws IOException, SQLException {
        BoardVO boardVO = new BoardVO();
        boardVO.setSeq(seq);

        return modelMapper.map(boardMapper.selectBoardBySeq(boardVO), ResponseSelectBoardBySeq.class);
    }

}
