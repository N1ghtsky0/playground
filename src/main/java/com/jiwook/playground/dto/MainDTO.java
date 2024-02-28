package com.jiwook.playground.dto;

import com.jiwook.playground.entity.MainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MainDTO {
    private String name;

    public MainDTO(MainEntity entity) {
        name = entity.getName();
    }
}
