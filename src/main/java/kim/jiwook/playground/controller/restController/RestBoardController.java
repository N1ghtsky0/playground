package kim.jiwook.playground.controller.restController;

import kim.jiwook.playground.service.BoardService;
import kim.jiwook.playground.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class RestBoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<?> restfulInsertBoard(@RequestBody BoardVO boardVO) throws IOException, SQLException {
        return ResponseEntity.ok(boardService.insertBoard(boardVO));
    }

    @GetMapping("/board")
    public ResponseEntity<?> restfulSelectAllBoard() throws IOException, SQLException {
        return ResponseEntity.ok(boardService.selectAllBoard());
    }

    @GetMapping("/board/{seq}")
    public ResponseEntity<?> restfulSelectBoardBySeq(@PathVariable("seq") long seq) throws IOException, SQLException {
        return ResponseEntity.ok(boardService.selectBoardBySeq(seq));
    }

}
