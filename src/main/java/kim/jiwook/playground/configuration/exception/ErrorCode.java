package kim.jiwook.playground.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // board
    NOT_FOUND_BOARD("게시글을 찾을 수 없습니다.", NOT_FOUND),

    // account
    DUPLICATED_LOGIN_ID("이미 가입된 로그인 아이디입니다.", BAD_REQUEST),
    BAD_REQUEST_LOGIN("아이디 또는 비밀번호가 틀렸습니다", BAD_REQUEST),
    FORBIDDEN_BOARD("글을 작성하거나 수정할 권한이 없습니다.", FORBIDDEN),
    INVALID_ACCOUNT_INFO("계정 정보를 찾을 수 없습니다.", BAD_REQUEST),
    ;

    private final String message;
    private final HttpStatus httpStatus;
}
