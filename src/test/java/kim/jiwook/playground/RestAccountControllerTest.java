package kim.jiwook.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import kim.jiwook.playground.Entity.Account;
import kim.jiwook.playground.configuration.SecurityConfig;
import kim.jiwook.playground.repository.AccountRepo;
import kim.jiwook.playground.vo.request.RequestSignIn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
public class RestAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountRepo accountRepo;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @AfterEach
    public void clean() {
        accountRepo.deleteAll();
    }

    @DisplayName("회원가입 - 성공")
    @Test
    public void 회원가입_성공() throws Exception {
        // given
        String uri = "/api/sign-up";

        RequestSignIn vo = new RequestSignIn();
        vo.setLoginId("testLoginId");
        vo.setLoginPwd("testLoginPwd");

        String requestBody = objectMapper.writeValueAsString(vo);

        // when & then
        mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful());
    }

    @DisplayName("회원가입 - 실패 - 양식")
    @Test
    public void 회원가입_실패_양식() throws Exception {
        // given
        String uri = "/api/sign-up";

        RequestSignIn vo = new RequestSignIn();
        vo.setLoginId("under8");
        vo.setLoginId("testLoginPwd");

        String requestBody = objectMapper.writeValueAsString(vo);

        // when & then
        mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("회원가입 - 실패 - 중복")
    @Test
    public void 회원가입_실패_중복() throws Exception {
        // given
        String uri = "/api/sign-up";

        RequestSignIn vo = new RequestSignIn();
        vo.setLoginId("testLoginId");
        vo.setLoginId("testLoginPwd");
        accountRepo.save(Account.from(vo, passwordEncoder));

        RequestSignIn newVo = new RequestSignIn();
        newVo.setLoginId("testLoginId");
        newVo.setLoginId("testLoginPwd");

        String requestBody = objectMapper.writeValueAsString(newVo);

        // when & then
        mvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
