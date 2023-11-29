package kim.jiwook.playground.controller.restController;

import io.swagger.v3.oas.annotations.media.Schema;
import kim.jiwook.playground.common.annotaion.*;
import kim.jiwook.playground.service.BoardService;
import kim.jiwook.playground.vo.request.RequestInsertBoard;
import kim.jiwook.playground.vo.response.ResponseSelectAllBoard;
import kim.jiwook.playground.vo.response.ResponseSelectBoardBySeq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class RestBoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    @authorizeLevel2
    public ResponseEntity<Long> restfulInsertBoard(@Schema(hidden = true) @AuthenticationPrincipal User user,
                                                   @Valid @RequestBody RequestInsertBoard requestVO) {
        return ResponseEntity.ok(boardService.insertBoard(requestVO, user));
    }

    @GetMapping("/board")
    @authorizeLevel1
    public ResponseEntity<List<ResponseSelectAllBoard>> restfulSelectAllBoard() {
        return ResponseEntity.ok(boardService.selectAllBoard());
    }

    @GetMapping("/board/{seq}")
    @authorizeLevel1
    public ResponseEntity<ResponseSelectBoardBySeq> restfulSelectBoardBySeq(@PathVariable("seq") long seq) {
        return ResponseEntity.ok(boardService.selectBoardBySeq(seq));
    }

}
