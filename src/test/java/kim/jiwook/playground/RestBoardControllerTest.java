package kim.jiwook.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import kim.jiwook.playground.vo.request.RequestInsertBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class RestBoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("게시글 작성 - 성공")
    public void 게시글_작성_성공() throws Exception {
        // given
        String uri = "/api/board";

        RequestInsertBoard vo = new RequestInsertBoard();
        vo.setTitle("testTitle");
        vo.setContent("testContent");
        vo.setAuthor("testAuthor");

        String requestBody = objectMapper.writeValueAsString(vo);

        // when & then
        mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("게시글 작성 - 실패")
    public void 게시글_작성_실패() throws Exception {
        // given
        String uri = "/api/board";

        RequestInsertBoard vo = new RequestInsertBoard();
        // 제목을 입력하지 않은 경우
        vo.setContent("testContent");
        vo.setAuthor("testAuthor");

        String requestBody = objectMapper.writeValueAsString(vo);

        // when & then
        mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is4xxClientError());
    }
}
