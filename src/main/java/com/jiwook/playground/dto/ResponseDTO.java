package com.jiwook.playground.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDTO {
    private String result;
    private String message;
    private Object data;

    public static ResponseDTO success(Object object) {
        return ResponseDTO.builder()
                .result("success")
                .data(object)
                .build();
    }

    public static ResponseDTO fail(String errorMsg) {
        return ResponseDTO.builder()
                .result("fail")
                .message(errorMsg)
                .build();
    }
}