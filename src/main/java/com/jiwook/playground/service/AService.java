package com.jiwook.playground.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AService {
    public void serviceMethod() {
        log.info("A serviceMethod");
    }
}
