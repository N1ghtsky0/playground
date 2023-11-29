package kim.jiwook.playground.service;

import kim.jiwook.playground.vo.request.RequestInsertBoard;
import kim.jiwook.playground.vo.response.ResponseSelectAllBoard;
import kim.jiwook.playground.vo.response.ResponseSelectBoardBySeq;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface BoardService {

    long insertBoard(RequestInsertBoard vo, User user);
    List<ResponseSelectAllBoard> selectAllBoard();
    ResponseSelectBoardBySeq selectBoardBySeq(long seq);

}
