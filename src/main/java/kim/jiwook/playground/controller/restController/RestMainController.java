package kim.jiwook.playground.controller.restController;

import kim.jiwook.playground.service.MainService;
import kim.jiwook.playground.vo.MainVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class RestMainController {
    private final MainService mainService;

    @PostMapping("/main")
    public ResponseEntity<?> RestfulInsertMain(@RequestBody MainVO mainVO) {
        return ResponseEntity.ok(mainService.insertMain(mainVO));
    }
}
