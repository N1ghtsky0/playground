package jiwook.kim.playground.service.impl;

import jiwook.kim.playground.Entity.Account;
import jiwook.kim.playground.Entity.Board;
import jiwook.kim.playground.dto.request.RequestInsertBoard;
import jiwook.kim.playground.dto.response.ResponseGetBoard;
import jiwook.kim.playground.dto.response.ResponseGetBoardSummary;
import jiwook.kim.playground.repository.AccountRepo;
import jiwook.kim.playground.repository.BoardRepo;
import jiwook.kim.playground.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final AccountRepo accountRepo;
    private final BoardRepo boardRepo;

    @Override
    public Long insertBoard(RequestInsertBoard requestInsertBoard, String uuid) {
        Account account = accountRepo.findAccountByUuid(uuid).orElseThrow(
                () -> new NullPointerException(String.format("{uuid - %s}의 정보를 찾을 수 없습니다.", uuid)));

        Board board = boardRepo.save(Board.builder()
                .title(requestInsertBoard.getTitle())
                .content(requestInsertBoard.getContent())
                .account(account)
                .build());

        return board.getSeq();
    }

    @Override
    public List<ResponseGetBoardSummary> getBoardList() {
        return boardRepo.findAllDesc()
                .stream().map(board -> new ResponseGetBoardSummary().from(board))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseGetBoard getBoard(Long seq) {
        return new ResponseGetBoard().from(boardRepo.findBoardBySeq(seq).orElseThrow(
                () -> new NullPointerException(String.format("%d 번 게시물을 찾을 수 없습니다.", seq))
        ));
    }

}
