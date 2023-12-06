package jiwook.kim.playground.controller;

import jiwook.kim.playground.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ViewIndexController {
    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("boardList", boardService.getBoardList());
        return "index";
    }
}
