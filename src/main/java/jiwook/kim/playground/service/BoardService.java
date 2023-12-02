package jiwook.kim.playground.service;

import jiwook.kim.playground.dto.request.RequestInsertBoard;
import jiwook.kim.playground.dto.response.ResponseGetBoard;
import jiwook.kim.playground.dto.response.ResponseGetBoardSummary;

import java.util.List;

public interface BoardService {
    Long insertBoard(RequestInsertBoard requestInsertBoard, String uuid);

    List<ResponseGetBoardSummary> getBoardList();

    ResponseGetBoard getBoard(Long seq);
}
