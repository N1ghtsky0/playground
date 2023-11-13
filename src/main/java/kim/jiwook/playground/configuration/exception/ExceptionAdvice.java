package kim.jiwook.playground.configuration.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> customException(CustomException e) {
        HashMap<String, Object> jsonData = new HashMap<>();
        jsonData.put("errorCode", e.getErrorCode());
        jsonData.put("errorStatus", e.getHttpStatus());
        jsonData.put("errorMessage", e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(jsonData);
    }
}
