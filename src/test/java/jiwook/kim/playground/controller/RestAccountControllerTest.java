package jiwook.kim.playground.controller;

import jiwook.kim.playground.Entity.Account;
import jiwook.kim.playground.TestContainerConfig;
import jiwook.kim.playground.dto.ApiResponse;
import jiwook.kim.playground.dto.request.RequestSignUp;
import jiwook.kim.playground.repository.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TestContainerConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestAccountControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    void clearAndCreateAdmin() {
        accountRepo.deleteAll();

        RequestSignUp requestSignUp = new RequestSignUp();
        requestSignUp.setLoginId("admin");
        requestSignUp.setLoginPwd("admin");
        requestSignUp.setName("admin");
        requestSignUp.setBirthDay("19700101");
        requestSignUp.setNickName("관리자");
        accountRepo.save(Account.createAdmin(requestSignUp, encoder));
    }

    @Test
    void 아이디_중복_체크() {
        // given
        String duplicatedLoginId = "admin";
        String newLoginId = "loginId";

        String expectedTrueUrl = "http://localhost:" + port + "/api/join?name=loginId&val=" + duplicatedLoginId;
        String expectedFalseUrl = "http://localhost:" + port + "/api/join?name=loginId&val=" + newLoginId;

        // when
        ResponseEntity<ApiResponse> expectedTrueResponseEntity = restTemplate.getForEntity(expectedTrueUrl, ApiResponse.class);
        ResponseEntity<ApiResponse> expectedFalseResponseEntityLoginId = restTemplate.getForEntity(expectedFalseUrl, ApiResponse.class);

        // then
        assertThat(expectedTrueResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(expectedTrueResponseEntity.getBody()).data()).isEqualTo(true);

        assertThat(expectedFalseResponseEntityLoginId.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(expectedFalseResponseEntityLoginId.getBody()).data()).isEqualTo(false);
    }

    @Test
    void 닉네임_중복_체크() {
        // given
        String duplicatedNickName = "관리자";
        String newNickName = "admin";

        String expectedTrueUrl = "http://localhost:" + port + "/api/join?name=nickName&val=" + duplicatedNickName;
        String expectedFalseUrl = "http://localhost:" + port + "/api/join?name=nickName&val=" + newNickName;

        // when
        ResponseEntity<ApiResponse> expectedTrueResponseEntity = restTemplate.getForEntity(expectedTrueUrl, ApiResponse.class);
        ResponseEntity<ApiResponse> expectedFalseResponseEntity = restTemplate.getForEntity(expectedFalseUrl, ApiResponse.class);

        // then
        assertThat(expectedTrueResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(expectedTrueResponseEntity.getBody()).data()).isEqualTo(true);

        assertThat(expectedFalseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(expectedFalseResponseEntity.getBody()).data()).isEqualTo(false);
    }

    @Test
    void signUp() {
    }

    @Test
    void getMyInfo() {
    }

    @Test
    void updateLoginPwd() {
    }

    @Test
    void logIn() {
    }
}