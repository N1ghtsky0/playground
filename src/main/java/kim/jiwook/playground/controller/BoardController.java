package kim.jiwook.playground.controller;

import kim.jiwook.playground.service.BoardService;
import kim.jiwook.playground.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    /**
     * 게시글 작성 페이지
     *
     * @param httpServletRequest HttpServletRequest
     * @param model Model
     * @return /board/list
     * @throws IOException
     * @throws SQLException
     */
    @RequestMapping("/board/list")
    public String boardListPage(HttpServletRequest httpServletRequest, Model model) throws IOException, SQLException {
        log.info(httpServletRequest.getRequestURI());

        model.addAttribute("boardVOList", boardService.selectAllBoard());
        return "/board/list";
    }

    /**
     * 게시글 작성 페이지
     *
     * @param httpServletRequest HttpServletRequest
     * @return /board/insert
     */
    @RequestMapping("/board/insert")
    public String boardInsertPage(HttpServletRequest httpServletRequest) {
        log.info(httpServletRequest.getRequestURI());

        return "/board/insert";
    }

    /**
     * 게시글 등록
     *
     * @param httpServletRequest HttpServletRequest
     * @param boardVO BoardVO
     * @return redirect:/board/list
     * @throws IOException
     * @throws SQLException
     */
    @RequestMapping("/board/insertProcess")
    public String boardInsertProcess(HttpServletRequest httpServletRequest, BoardVO boardVO) throws IOException, SQLException {
        log.info(httpServletRequest.getRequestURI());

        boardService.insertBoard(boardVO);
        return "redirect:/board/list";
    }

}
