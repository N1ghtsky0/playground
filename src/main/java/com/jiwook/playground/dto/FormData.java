package com.jiwook.playground.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormData {
    private String name;
    private String fruit;

    public String toString() {
        return "{" +
                "name=" + name + ", " +
                "fruit=" + fruit +
                "}";
    }
}
