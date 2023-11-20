package kim.jiwook.playground.service.impl;

import kim.jiwook.playground.Entity.Board;
import kim.jiwook.playground.configuration.exception.CustomException;
import kim.jiwook.playground.repository.BoardRepo;
import kim.jiwook.playground.service.BoardService;
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

import static kim.jiwook.playground.configuration.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepo boardRepo;

    private final ModelMapper modelMapper;

    @Override
    public long insertBoard(RequestInsertBoard vo) throws IOException, SQLException {
        if (vo.getAuthor() == null || vo.getAuthor().isEmpty()) {
            vo.setAuthor("anonymous");
        }

        Board board = boardRepo.save(modelMapper.map(vo, Board.class));
        return board.getSeq();
    }

    @Override
    public List<ResponseSelectAllBoard> selectAllBoard() throws IOException, SQLException {
        return boardRepo.findAll()
                .stream()
                .map(board -> modelMapper.map(board, ResponseSelectAllBoard.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseSelectBoardBySeq selectBoardBySeq(long seq) throws IOException, SQLException {
        return modelMapper.map(boardRepo.findBoardBySeq(seq).orElseThrow(() -> new CustomException(NOT_FOUND_BOARD)), ResponseSelectBoardBySeq.class);
    }

}
