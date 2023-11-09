package kim.jiwook.playground.controller.restController;

import kim.jiwook.playground.service.BoardService;
import kim.jiwook.playground.vo.request.RequestInsertBoard;
import kim.jiwook.playground.vo.response.ResponseSelectAllBoard;
import kim.jiwook.playground.vo.response.ResponseSelectBoardBySeq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class RestBoardController {
    private final BoardService boardService;

    /**
     * 게시글 작성 API
     *
     * @param requestVO RequestInsertBoard
     * @return ResponseEntity 글이 저장되면 1, 저장되지 않으면 0
     * @throws IOException
     * @throws SQLException
     */
    @PostMapping("/board")
    public ResponseEntity<Integer> restfulInsertBoard(@RequestBody RequestInsertBoard requestVO) throws IOException, SQLException {
        return ResponseEntity.ok(boardService.insertBoard(requestVO));
    }

    /**
     * 전체 게시글 조회
     *
     * @return List<ResponseSelectAllBoard>
     * @throws IOException
     * @throws SQLException
     */
    @GetMapping("/board")
    public ResponseEntity<List<ResponseSelectAllBoard>> restfulSelectAllBoard() throws IOException, SQLException {
        return ResponseEntity.ok(boardService.selectAllBoard());
    }

    /**
     * 게시글 상세 조회
     *
     * @param seq 게시글 시퀀스
     * @return ResponseEntity ResponseSelectBoardBySeq
     * @throws IOException
     * @throws SQLException
     */
    @GetMapping("/board/{seq}")
    public ResponseEntity<ResponseSelectBoardBySeq> restfulSelectBoardBySeq(@PathVariable("seq") long seq) throws IOException, SQLException {
        return ResponseEntity.ok(boardService.selectBoardBySeq(seq));
    }

}
