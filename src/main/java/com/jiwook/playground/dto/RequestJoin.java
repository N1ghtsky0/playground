package com.jiwook.playground.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestJoin {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
}
