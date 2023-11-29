package kim.jiwook.playground.controller;

import kim.jiwook.playground.common.annotaion.authorizeLevel1;
import kim.jiwook.playground.service.BoardService;
import kim.jiwook.playground.vo.request.RequestInsertBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    /**
     * 게시글 목록 페이지
     *
     * @param httpServletRequest HttpServletRequest
     * @param model Model
     * @return /board/list
     */
    @RequestMapping("/board/list")
    public String boardListPage(HttpServletRequest httpServletRequest, Model model) {
        log.info(httpServletRequest.getRequestURI());

        model.addAttribute("boardVOList", boardService.selectAllBoard());
        model.addAttribute("tab", "blog");
        return "/board/list";
    }

    /**
     * 게시글 작성 페이지
     *
     * @param httpServletRequest HttpServletRequest
     * @return /board/insert
     */
    @RequestMapping("/board/insert")
    @authorizeLevel1
    public String boardInsertPage(HttpServletRequest httpServletRequest, Model model) {
        log.info(httpServletRequest.getRequestURI());

        model.addAttribute("tab", "blog");
        return "/board/insert";
    }

    /**
     * 게시글 등록
     *
     * @param httpServletRequest HttpServletRequest
     * @param requestVO RequestInsertBoard
     * @return redirect:/board/list
     */
    @RequestMapping("/board/insertProcess")
    @authorizeLevel1
    public String boardInsertProcess(HttpServletRequest httpServletRequest, RequestInsertBoard requestVO,
                                     @AuthenticationPrincipal User user) {
        log.info(httpServletRequest.getRequestURI());

        boardService.insertBoard(requestVO, user);
        return "redirect:/board/list";
    }

    /**
     * 게시글 상세조회 페이지
     *
     * @param httpServletRequest HttpServletRequest
     * @param seq 게시글 시퀀스
     * @param model Model
     * @return /board/detail
     */
    @RequestMapping("/board/detail/{seq}")
    public String boardDetailPage(HttpServletRequest httpServletRequest,
                                  @PathVariable("seq") String seq,
                                  Model model) {
        log.info(httpServletRequest.getRequestURI());

        model.addAttribute("boardVO", boardService.selectBoardBySeq(Long.parseLong(seq)));
        model.addAttribute("tab", "blog");
        return "/board/detail";
    }

}
