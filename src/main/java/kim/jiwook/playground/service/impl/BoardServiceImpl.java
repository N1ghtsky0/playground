package kim.jiwook.playground.service.impl;

import kim.jiwook.playground.Entity.Board;
import kim.jiwook.playground.configuration.exception.CustomException;
import kim.jiwook.playground.repository.AccountRepo;
import kim.jiwook.playground.repository.BoardRepo;
import kim.jiwook.playground.service.BoardService;
import kim.jiwook.playground.vo.request.RequestInsertBoard;
import kim.jiwook.playground.vo.response.ResponseSelectAllBoard;
import kim.jiwook.playground.vo.response.ResponseSelectBoardBySeq;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static kim.jiwook.playground.configuration.exception.ErrorCode.FORBIDDEN_BOARD;
import static kim.jiwook.playground.configuration.exception.ErrorCode.NOT_FOUND_BOARD;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepo boardRepo;
    private final AccountRepo accountRepo;

    private final ModelMapper modelMapper;

    @Override
    public long insertBoard(RequestInsertBoard vo, User user) {
        if (vo.getAuthor() == null || vo.getAuthor().isEmpty()) {
            vo.setAuthor("anonymous");
        }

        Board board = modelMapper.map(vo, Board.class);
        board.setAccount(accountRepo.findAccountByUuid(user.getUsername())
                .orElseThrow(() -> new CustomException(FORBIDDEN_BOARD)));

        return boardRepo.save(board).getSeq();
    }

    @Override
    public List<ResponseSelectAllBoard> selectAllBoard() {
        return boardRepo.findAll()
                .stream()
                .map(board -> modelMapper.map(board, ResponseSelectAllBoard.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseSelectBoardBySeq selectBoardBySeq(long seq) {
        return modelMapper.map(boardRepo.findBoardBySeq(seq).orElseThrow(() -> new CustomException(NOT_FOUND_BOARD)), ResponseSelectBoardBySeq.class);
    }

}
