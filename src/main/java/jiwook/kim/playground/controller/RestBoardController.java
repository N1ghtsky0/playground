package jiwook.kim.playground.controller;

import jiwook.kim.playground.dto.ApiResponse;
import jiwook.kim.playground.dto.request.RequestInsertBoard;
import jiwook.kim.playground.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestBoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<ApiResponse> insertBoard(@RequestBody RequestInsertBoard requestInsertBoard,
                                                   Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(boardService.insertBoard(requestInsertBoard, authentication.getName())));
    }

    @GetMapping("/board/{seq}")
    public ResponseEntity<ApiResponse> getAllBoard(@PathVariable("seq") Long seq) {
        ApiResponse apiResponse;
        if (seq == 0) {
            apiResponse = ApiResponse.success(boardService.getBoardList());
        } else {
            apiResponse = ApiResponse.success(boardService.getBoard(seq));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }

}
