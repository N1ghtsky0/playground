package com.jiwook.playground.dto;

import com.jiwook.playground.entity.SubLazyEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubDTO {
    private String name;
    private MainDTO mainDTO;

    public SubDTO(SubLazyEntity entity) {
        name = entity.getName();
        mainDTO = new MainDTO(entity.getEntity());
    }
}
