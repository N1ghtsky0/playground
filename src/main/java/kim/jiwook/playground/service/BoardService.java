package kim.jiwook.playground.service;

import kim.jiwook.playground.vo.request.RequestInsertBoard;
import kim.jiwook.playground.vo.response.ResponseSelectAllBoard;
import kim.jiwook.playground.vo.response.ResponseSelectBoardBySeq;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    long insertBoard(RequestInsertBoard vo) throws IOException, SQLException;
    List<ResponseSelectAllBoard> selectAllBoard() throws IOException, SQLException;
    ResponseSelectBoardBySeq selectBoardBySeq(long seq) throws IOException, SQLException;

}
